package mkcloudadmin.model.base;

/**
 * api返回错误码.
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午3:43:56 hewenbin
 */
public enum CodeEnum {
    /*
     * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好
     * 在默认 code 不够用的情况下，可以添加具体业务code，注意：不建议添加业务code
     */
    succ("0000000", null),
    paramEmpty("0000001", "参数为空"),
    paramLack("0000002", "参数不足"),
    dataExist("0000003", "数据已存在"),
    dataNotExist("0000004", "数据不存在"),
    dbException("0000005", "数据库异常"),
    fail("0000010", "失败了"),
    paramInvalid("0000011", "参数不合法"),
    paramOverLimit("0000012", "参数超过限制"),
    paramOverLenLimit("0000013", "参数长度不合法"),
    systemError("9999999","网络返回错误"),
    
    loginParamLack("0101001", "参数缺失"),
    loginImgValidateFail("0101002", "图片验证码不正确"),
    loginSmsValidateFail("0101003", "短信验证码不正确"),
    
    imgValidateInvalid("0102001", "参数不合法"),
    imgValidateFail("0102002", "图片验证码不正确"),
    
    smsValidateInvalid("0103001", "参数不合法"),
    smsImgValidateFail("0103002", "图片验证码不正确"),
    
    bankCardParamInvalid("0103001", "参数不合法"),
    
    accountPwdParamInvalid("0104001", "参数不合法"),
    accountPwdSmsValidateFail("0104002", "短信验证码不正确"),
    
    
    financeParamNull("0201000","提现参数为空，请核查!"),
    sendMsgError("0201001","短信发送失败"),
    userNotExist("0201002","用户不存在"),
    extendWithDrawMoney("0201003","超出可提现金额"),
    pwdError("0201004","提现密码不对"),


    system_error("0300000", "系统异常"),
    user_existed("0300001","该用户是推广员"),
    user_notexisted("0300002","该用户不是推广员"),
    success("0000015", "成功"),
    failure("0000016", "失败了"),
   product_not("0000017","该机构名称不存在"),

    //业务code
    example("", "");
    private String code;
    private String msg;

    CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
