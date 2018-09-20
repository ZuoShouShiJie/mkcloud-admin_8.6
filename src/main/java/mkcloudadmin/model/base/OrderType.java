package mkcloudadmin.model.base;

/**
 * 订单状态
 */
public enum OrderType {
    /*
     * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好
     * 在默认 code 不够用的情况下，可以添加具体业务code，注意：不建议添加业务code
     */

    debit("debit","提现"),
    charge("charge","充值"),
    login("login","注册"),
    interTransfer("interTransfer","内部转账"),

    //业务code

    example("", "");
    private String code;
    private String msg;

    OrderType(String code, String msg) {
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
