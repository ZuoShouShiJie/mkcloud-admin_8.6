package mkcloudadmin.service.questionandanswers.impl;

import mkcloudadmin.mapper.mkcloud.FinanceQuestionAndAnswerMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceQuestionAndAnswer;
import mkcloudadmin.service.questionandanswers.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QAServiceImpl implements QAService {

    @Autowired
    private FinanceQuestionAndAnswerMapper questionAndAnswerMapper;

    public Map<String, Object> queryQuesttions(Integer pageNum, Integer pageSize, String title){
        Integer startRow = (pageNum-1)*pageSize;
        Page<FinanceQuestionAndAnswer> qaPage = new Page<>(pageSize, Long.valueOf(pageNum));
        Long total = questionAndAnswerMapper.queryQACount(title);
        List<FinanceQuestionAndAnswer> questionAndAnswerList = new ArrayList<>();
        int count = 0;
        if (total>0){
            questionAndAnswerList = questionAndAnswerMapper.queryAll(qaPage,title);
            count=total.intValue();
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        if(questionAndAnswerList!=null && questionAndAnswerList.size()>0){
            for (FinanceQuestionAndAnswer questionAndAnswer :questionAndAnswerList) {
                Map<String, Object> info = new HashMap<>();
                info.put("id",questionAndAnswer.getId());
                info.put("title", questionAndAnswer.getTitle());
                info.put("content",questionAndAnswer.getContent());
                info.put("seqNo",questionAndAnswer.getSeqNo());
                dataList.add(info);
            }
        }
        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "");
        res.put("count",count);
        res.put("data",dataList);

        return res;
    }

    public Map<String, Object> queryQuesttionById(Long id){
        FinanceQuestionAndAnswer questionAndAnswer = questionAndAnswerMapper.selectByPrimaryKey(id);
        int count = 0;

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "");
        res.put("count",count);
        res.put("data",questionAndAnswer);

        return res;
    }

    public Map<String, Object> addQuesttions(Map<String, String> param){

        String title = param.get("title");
        String content = param.get("content");
        Integer seqNo = new Integer(param.get("seqNo"));
        FinanceQuestionAndAnswer financeQuestionAndAnswer = new FinanceQuestionAndAnswer();
        financeQuestionAndAnswer.setTitle(title);
        financeQuestionAndAnswer.setContent(content);
        financeQuestionAndAnswer.setSeqNo(seqNo);


        int i = questionAndAnswerMapper.insertSelective(financeQuestionAndAnswer);
        int count = 0;
        List<Map<String, Object>> dataList = new ArrayList<>();

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "");
        res.put("count",count);
        res.put("data",dataList);

        return res;
    }

    public Map<String, Object> deleteQuesttions( Long id){

        int i = questionAndAnswerMapper.updateToDeleteByPrimaryKey(id);
        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "");
        res.put("count",0);
        res.put("data","");

        return res;
    }

    public Map<String, Object> editQuesttions( Map<String, String> param){

        Long id = Long.valueOf(param.get("id"));
        String title = param.get("title");
        String content = param.get("content");
        Integer seqNo = new Integer(param.get("seqNo"));

        FinanceQuestionAndAnswer financeQuestionAndAnswer = new FinanceQuestionAndAnswer();
        financeQuestionAndAnswer.setId(id);
        financeQuestionAndAnswer.setTitle(title);
        financeQuestionAndAnswer.setContent(content);
        financeQuestionAndAnswer.setSeqNo(seqNo);
        int i = questionAndAnswerMapper.updateByPrimaryKeySelective(financeQuestionAndAnswer);

        Map<String, Object>  res = new HashMap<>();
        res.put("code",0);
        res.put("msg", "");
        res.put("count",0);
        res.put("data","");

        return res;
    }
}
