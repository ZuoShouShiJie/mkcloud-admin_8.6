/*
package mkcloudadmin.job;

import com.alibaba.fastjson.JSON;
import mkcloudadmin.mapper.mkcloud.FinanceOrderMapper;
import mkcloudadmin.mapper.mkcloud.FinanceUserAccountMapper;
import mkcloudadmin.model.base.OrderStatus;
import mkcloudadmin.model.base.OrderType;
import mkcloudadmin.model.mkcloud.po.FinanceOrder;
import mkcloudadmin.model.mkcloud.po.FinanceUserAccount;
import mkcloudadmin.util.ArithUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import java.math.BigDecimal;
import java.util.*;

*/
/**
 * @author yaolei
 * @Title: IncomeMoneyToCanWithDraw
 * @ProjectName mkcloud-admin
 * @Description: 
 * @date 2018/7/18下午1:52
 *//*


@Component
public class IncomeMoneyToCanWithDraw implements SchedulingConfigurer {


    private static final Logger logger = LoggerFactory.getLogger(IncomeMoneyToCanWithDraw.class);

    @Value("${incomeMoney.to.canwithdraw}")
    private String cron;

    @Value("${unfreeze.incomeMoney.day}")
    private int day;

    @Value("${rotate.lock.time}")
    private int lockTime;

    @Autowired
    public FinanceOrderMapper orderMapper;

    @Autowired
    public FinanceUserAccountMapper accountMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            System.out.println("动态修改定时任务cron参数，当前时间：" + new Date());
            handle();
        }, triggerContext -> {
            // 定时任务触发，可修改定时任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });
    }

    private void handle() {

        List<FinanceUserAccount> acclist = accountMapper.selectAccounts();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR,day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        Map<FinanceUserAccount,List<FinanceOrder>> map = new HashMap<>();

        for(FinanceUserAccount account : acclist){
            List<FinanceOrder> orderlist = orderMapper.selectOrders(account.getUserId(),c.getTime());
            for(FinanceOrder order : orderlist){
              if(map.containsKey(account)){
                  map.get(account).add(order);
              }else{
                  List<FinanceOrder> accList = new ArrayList<>();
                  accList.add(order);
                  map.put(account,accList);
              }
            }
            orderlist = null;
        }
        acclist = null;
        map.forEach((key, value) -> {
            try {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        try {
                            downIncomeAndUpWithDrawAndSaveOrder(key,value);
                        } catch (Exception e) {
                            logger.info("#updateAccAndSaveOrder--e={}", e);
                            transactionStatus.setRollbackOnly();
                        }
                    }
                });
            } catch (Exception e) {
                logger.info("#更新账户信息&记录流水异常，e={}", e);
            }
        });
    }


    private void downIncomeAndUpWithDrawAndSaveOrder(FinanceUserAccount account,List<FinanceOrder> list) throws Exception {
        //
        //userId对应的，冻结金额转可提现金额
        BigDecimal sum = BigDecimal.ZERO;

        for(FinanceOrder order : list){
              logger.info("#downIncomeAndUpWithDrawAndSaveOrder--money={}", order.getMoney());
              sum = ArithUtil.add(sum,order.getMoney());
        }
        logger.info("#downIncomeAndUpWithDrawAndSaveOrder--sum={}", sum);
        Long l = System.currentTimeMillis();

        //乐观锁自旋
        for (;;) {

            logger.info("#downIncomeAndUpWithDrawAndSaveOrder--account={}",  JSON.toJSONString(account));
            int i =  accountMapper.upCanWithDrawMoneyAndDownIncome(account.getId(),sum,account.getVersionNum());
            logger.info("#upCanWithDrawMoneyAndDownIncome--i={}",i);
            if(i == 1){
                logger.info("#upCanWithDrawMoneyAndDownIncome--list={}",JSON.toJSONString(list));

                int j = orderMapper.updateOrdersByIds(list);
                logger.info("#upCanWithDrawMoneyAndDownIncome--updateOrdersByIds={}",j);

                FinanceOrder order = new FinanceOrder();
                Random rand = new Random();
                int h = rand.nextInt(899) + 100;
                order.setMoney(sum);
                order.setSerialId(System.currentTimeMillis()+""+h);
                order.setUserId(account.getUserId());
                order.setTransType(OrderType.interTransfer.getCode());
                order.setStatus(OrderStatus.canWithDraw.getCode());
                logger.info("#upCanWithDrawMoneyAndDownIncome--tranfer={}",JSON.toJSONString(order));
                i = orderMapper.insertSelective(order);
                logger.info("#upCanWithDrawMoneyAndDownIncome--i={},j={}",i,j);

                if(j>=1 && i ==1){
                    return;
                }else{
                    logger.info("#updateOrdersByIds--j={}",j);
                    throw new Exception("更新失败");
                }
            }else{
                account =  accountMapper.selectByPrimaryKey(account.getId());
                logger.info("#account={}",JSON.toJSONString(account));
            }
            Long end = System.currentTimeMillis();
            if(1000*lockTime <= end-l){
                logger.info("#获取锁时间超时，accountId={},sum={}",account.getId(),sum);
                throw new Exception("获取锁时间超时,更新失败");
            }
        }
    }


    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getCron() {
        return this.cron;
    }

    public static  void  main(String[] args) throws InterruptedException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR,-15);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        System.out.println(c.getTime());
        Long l = System.currentTimeMillis();
        System.out.println(l);
        Thread.sleep(10000);
        Long end = System.currentTimeMillis();
        System.out.println(end-l);

    }
}

*/
