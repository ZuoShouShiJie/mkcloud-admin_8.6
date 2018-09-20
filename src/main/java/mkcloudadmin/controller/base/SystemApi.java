package mkcloudadmin.controller.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用系统接口.
 * @author hewenbin
 * @version v1.0 2018年7月4日 下午5:11:19 hewenbin
 */
@RestController
public class SystemApi {
	/**
	 * <pre>
	 * @api {GET} /monitor 可用性监控服务
	 * @apiName monitor 
	 * @apiGroup SYSTEM
	 * @apiVersion 0.1.0
	 * @apiDescription HTTP code =200 表示应用正常，忽略其他返回值
	 * @apiSuccess {Boolean} succeed 是否成功
	 * @apiSuccess {String} errorCode 结果码
	 * @apiSuccess {String} errorMessage 消息说明
	 * @apiSuccess {JSON} data 数据
	 * @apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
     *   "succeed",true,
	 *   "data":null
	 *  }
	 * @apiError 0000000 成功
	 * @apiError 9999999 网络返回错误
	 * @apiError 0000011 参数不合法
	 * @apiError 0000010 失败
	 * </pre>
	 * @return
	 * @author hewenbin
	 * @version SystemApi.java, v1.0 2018年7月5日 下午3:59:47 hewenbin
	 */
	@GetMapping("monitor")
	public String monitor(){
		return "success";
	}
	
}
