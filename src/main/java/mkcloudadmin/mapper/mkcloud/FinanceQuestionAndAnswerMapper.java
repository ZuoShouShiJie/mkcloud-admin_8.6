package mkcloudadmin.mapper.mkcloud;

import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.po.FinanceQuestionAndAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceQuestionAndAnswerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceQuestionAndAnswer record);

    int insertSelective(FinanceQuestionAndAnswer record);

    FinanceQuestionAndAnswer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceQuestionAndAnswer record);

    int updateByPrimaryKey(FinanceQuestionAndAnswer record);

    /**
     * （根据标题）查询所有Q&A
     *
     * @param page
     * @param title
     * @return java.util.List<mkcloudadmin.model.mkcloud.po.FinanceQuestionAndAnswer>
     * @throws
     * @author panzhongkang
     * @date 2018/7/12 20:27
     */
    List<FinanceQuestionAndAnswer> queryAll(@Param("page") Page<FinanceQuestionAndAnswer> page, @Param("title") String title);

    /**
     * （根据标题）查询所有Q&A数
     *
     * @param title
     * @return java.lang.Long
     * @throws
     * @author panzhongkang
     * @date 2018/7/12 20:28
     */
    Long queryQACount(@Param("title") String title);

    /**
     * 根据id逻辑删除Q&A
     *
     * @param id
     * @return int
     * @throws
     * @author panzhongkang
     * @date 2018/7/12 20:29
     */
    int updateToDeleteByPrimaryKey(Long id);
}