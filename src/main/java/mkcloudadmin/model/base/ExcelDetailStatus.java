package mkcloudadmin.model.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单状态
 */
public enum ExcelDetailStatus {
    /*
     * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好
     * 在默认 code 不够用的情况下，可以添加具体业务code，注意：不建议添加业务code
     */

    success("00","成功"),
    noProduct("02","无产品信息"),
    noUserInfo("03","无匹配信息"),
    moreUserInfo("04","多条匹配信息"),
    sysError("05","系统异常"),
    dbError("06","数据库异常"),
    parseDataError("07","解析数据异常"),
    ;

    //业务code
    private static final Map<String,String> param = new HashMap<String, String>();

    static{
        for(ExcelDetailStatus orderstatus : ExcelDetailStatus.values()){
            param.put(orderstatus.getCode(), orderstatus.getMsg());
        }
    }

    private String code;
    private String msg;

    ExcelDetailStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<String, String> getParam() {
        return param;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
