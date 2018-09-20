package mkcloudadmin.model.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yaolei
 * @Title: Constant
 * @ProjectName mkcloud-app
 * @Description: 常亮类
 * @date 2018/7/10上午9:55
 */
public class Constant {

    /**申请提现title**/
    public static final String  transfer_title = "用户申请提现" ;

    /**DE代表借记卡，CR代表信用卡，其他值为非法**/
    public static final String  card_type_de = "DE" ;

    /**DE代表借记卡，CR代表信用卡，其他值为非法**/
    public static final String  card_type_cr = "CR" ;
    
    /** 支持转账的银行列表*/
    public static final Map<String, String> bank_map;
    
    /** 密码类型*/
    public enum PwdType{
    		withdraw(); // 提现
    		PwdType() {
        }
    }
    static {
    		bank_map = new HashMap<>();
    		bank_map.put("EVERCNBJ","光大银行");
    		bank_map.put("FJIBCNBA","兴业银行");
    		bank_map.put("ABOCCNBJ","农业银行");
    		bank_map.put("GDBKCN22","广发银行");
    		bank_map.put("SPDBCNSH","浦发银行");
    		bank_map.put("PCBCCNBJ","建设银行");
    		bank_map.put("HZCBCN2H","杭州银行");
    		bank_map.put("CQCBCN22","重庆银行");
    		bank_map.put("BKNBCN2N","宁波银行");
    		bank_map.put("SZDBCNBS","平安银行");
    		bank_map.put("ICBKCNBJ","工商银行");
    		bank_map.put("COMMCNSH","交通银行");
    		bank_map.put("CMBCCNBS","招商银行");
    		bank_map.put("BKCHCNBJ","中国银行");
    		bank_map.put("LWCBCNBJ","莱商银行");
    		bank_map.put("BOSHCNSH","上海银行");
    		bank_map.put("BOJSCNBN","江苏银行");
    		bank_map.put("HXBKCNBJ","华頁银行");
    		bank_map.put("CIBKCNBJ","中信银行");
    		bank_map.put("MSBCCNBJ","民生银行");
    		bank_map.put("PSBCCNBJ","邮政储蓄银行");
    		bank_map.put("GZCBCN22","广州银行");
    		bank_map.put("BJCNCNBJ","北京银行");
    		bank_map.put("LYCBCNBL","临商银行");
    }

}
