package mkcloudadmin.controller.base;

import mkcloudadmin.service.aliyunOss.StoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: mkcloud-admin
 *
 * @description: 文件上载controller层
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-13 10:53
 **/
@Controller
@RequestMapping("file")
public class FileUploadController {
    @Resource
    private StoreClient storeClient;

    /**
     * 上载图片
     * @author moruihai
     */
    @RequestMapping(method = RequestMethod.POST, path = "uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request) throws IOException {

        String oldName = file.getOriginalFilename();
        InputStream is = file.getInputStream();

        String returnUrl = "";
        Map<String, Object>  res = new HashMap<>();
        try{
            storeClient.init();
            returnUrl = storeClient.putObject(oldName,is);
        }catch (Exception e){
            System.out.println("save:" + e);
            res.put("code",1);
            res.put("msg", "上载异常");
            res.put("src",returnUrl);
            return res;
        }

        res.put("code",0);
        res.put("msg", "上载成功");
        res.put("src",returnUrl);
        return res;
    }
}
