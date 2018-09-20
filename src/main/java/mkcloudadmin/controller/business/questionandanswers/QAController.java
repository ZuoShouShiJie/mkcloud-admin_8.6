package mkcloudadmin.controller.business.questionandanswers;


import mkcloudadmin.controller.base.BaseApi;
import mkcloudadmin.service.questionandanswers.QAService;
import mkcloudadmin.ueditor.ActionEnter;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QAController extends BaseApi {

    @Autowired
    private QAService qaService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/queryQuesttions")
    public Map<String, Object> queryQuesttionsJson(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize,
                                                   @RequestParam(value = "title", required = false) String title) {
        Map<String, Object> res = qaService.queryQuesttions(pageNum, pageSize, title);
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/queryQuesttionById")
    public Map<String, Object> queryQuesttionsById(@RequestParam(value = "id", required = false) Long id) {
        Map<String, Object> res = qaService.queryQuesttionById(id);
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/addQuesttions")
    public Map<String, Object> saveQuesttions(@RequestParam Map<String, String> param) {

        Map<String, Object> res = qaService.addQuesttions( param);
        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/deleteQuesttions")
    public Map<String, Object> deleteQuesttions(@RequestParam Map<String, String> data) {
        Long id;
        Map<String, Object> res = null;
        String idArrStr = data.get("idarr");
        if (idArrStr.indexOf(",") > -1) {
            String[] idArr = idArrStr.split(",");
            for (String idStr : idArr) {
                id = Long.valueOf(idStr);
                res = qaService.deleteQuesttions(id);
            }
        } else {
            id = Long.valueOf(idArrStr);
            res = qaService.deleteQuesttions(id);
        }


        return res;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/editQuesttion")
    public Map<String, Object> editQuesttions(@RequestParam Map<String, String> param) {

        Map<String, Object> res = qaService.editQuesttions( param);
        return res;
    }


    /**
     * 百度富文本编辑器：图片上传
     *
     * @param request
     * @param response
     * @throws JSONException
     * @throws IOException
     */
    @RequestMapping("/config")
    public void config(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String exec = new ActionEnter(request, rootPath).exec();
        PrintWriter writer = response.getWriter();
        writer.write(exec);
        writer.flush();
        writer.close();
    }
}
