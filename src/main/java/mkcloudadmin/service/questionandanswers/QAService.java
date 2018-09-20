package mkcloudadmin.service.questionandanswers;

import java.util.Map;

public interface QAService {
    /**
     * （根据标题）查询所有Q&A
     *
     * @param pageNum
     * @param pageSize
     * @param title
     * @return Map<String , Object>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 11:29
     */
    Map<String, Object> queryQuesttions(Integer pageNum, Integer pageSize, String title);

    /**
     * 根据id查Q&A
     *
     * @param id
     * @return Map<String , Object>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 11:31
     */
    Map<String, Object> queryQuesttionById(Long id);

    /**
     * 添加Q&A
     *
     * @param title
     * @param content
     * @return Map<String , Object>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 11:30
     */
    Map<String, Object> addQuesttions(Map<String, String> param);

    /**
     * 删除Q&A
     *
     * @param id
     * @return Map<String , Object>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 11:31
     */
    Map<String, Object> deleteQuesttions(Long id);

    /**
     * 编辑Q&A
     *
     * @param id
     * @param title
     * @param content
     * @return Map<String , Object>
     * @throws
     * @author panzhongkang
     * @date 2018/7/13 11:31
     */
    Map<String, Object> editQuesttions(Map<String, String> param);
}
