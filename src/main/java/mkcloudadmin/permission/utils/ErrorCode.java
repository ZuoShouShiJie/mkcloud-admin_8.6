//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.utils;

public enum ErrorCode {
    SUCCESS("0000000", Boolean.TRUE.booleanValue(), "success"),
    NETWORK_ERROR("1201002", "权限服务网络异常"),
    USER_SESSION_NOT_EXIST("1202103", "用户session不存在"),
    USER_NOT_PERMISSION("1202104", "用户没有权限");

    private String errorCode;
    private boolean isSuccess;
    private String errorMessage;

    private ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.isSuccess = Boolean.FALSE.booleanValue();
        this.errorMessage = errorMessage;
    }

    private ErrorCode(String errorCode, boolean isSuccess, String errorMessage) {
        this.errorCode = errorCode;
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public static mkcloudadmin.permission.utils.ErrorCode getCode(String message) {
        mkcloudadmin.permission.utils.ErrorCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            mkcloudadmin.permission.utils.ErrorCode code = var1[var3];
            if (code.getErrorMessage().equals(message)) {
                return code;
            }
        }

        return null;
    }

    public static mkcloudadmin.permission.utils.ErrorCode getMessage(String code) {
        mkcloudadmin.permission.utils.ErrorCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            mkcloudadmin.permission.utils.ErrorCode errorCode = var1[var3];
            if (errorCode.getErrorCode().equals(code)) {
                return errorCode;
            }
        }

        return null;
    }
}
