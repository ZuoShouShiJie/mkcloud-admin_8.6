package mkcloudadmin.model.base;

public enum BusinessPeopleTypeEnum {
    PEOPLE_TYPE_IN("0","内部推广员"),
    PEOPLE_TYPE_PEOPLE("0","普通用户"),
    PEOPLE_TYPE_IN1("1","内部推广员"),
    PEOPLE_TYPE_OUT("1","外部推广员"),
    PEOPLE_TYPE_OUT1("2","外部推广员");
    private String code;
    private String msg;

    BusinessPeopleTypeEnum(String code, String msg) {
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
