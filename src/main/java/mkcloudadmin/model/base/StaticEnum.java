package mkcloudadmin.model.base;

public enum StaticEnum {
    //推广员信息状态
     INVALID("0","无效"),
    EFFECTIVE("1","有效"),
queryAll("0","当日办件量"),
    manthQueryAll("1","当月办件量"),

    //银行反馈状态为成功
   bankSuccess_Import("0001","成功"),
    //表示该数据已经读取过
    version("0","已读"),
    version_no("1","未读"),
//申请记录表状态
    SUCCESS("1","成功"),
    APPLYING("0","申请中"),

     //支付状态
    NO_PAID("0","未支付"),
    PAID("1","已支付"),

     //预付款 操作类型
     adjust_type_add("1","新增"),
    adjust_type_out("-1","转出"),

    //会员表业务办理状态
    member_sussess("1","有效"),
    member_fail("0","无效"),
    member_shen("2","审核中"),
     //申请记录数据状态
     apply_for_success("成功","成功"),


     //预付款记录操作类型
    djustType_add("1","新增"),
    djustType_subtract("-1","转出");
     private String code;
    private String msg;



    StaticEnum(String code, String msg) {
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
