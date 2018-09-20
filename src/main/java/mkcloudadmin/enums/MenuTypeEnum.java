package mkcloudadmin.enums;

/**
 * Created by daixiaohu on 2018/8/23.
 */
public enum MenuTypeEnum {
    ACPI("1","模块"),
    AP("2","菜单")
    ;
    private String code;
    private String msg;


    public static MenuTypeEnum parser(String code) {
        for (MenuTypeEnum $switch : MenuTypeEnum.values()) {
            try {
                if ($switch.code.equals(code)) {
                    return $switch;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }


    MenuTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
