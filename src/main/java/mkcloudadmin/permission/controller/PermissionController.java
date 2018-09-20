//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.controller;

import mkcloudadmin.permission.vo.BaseResponse;
import mkcloudadmin.permission.vo.UserLoginReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping({"/permission"})
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PermissionController() {
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public BaseResponse permissionLogin(@RequestBody UserLoginReq req, HttpServletRequest request, HttpServletResponse response) {
        this.logger.info("======>permissionLogin:req={}", req);
        return null;
    }
}
