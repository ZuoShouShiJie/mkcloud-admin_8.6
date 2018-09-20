//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private String errorCode;
    private Boolean succeed = false;
    private String errorMessage;
    private Object data;

    public BaseResponse() {
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getSucceed() {
        return this.succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static mkcloudadmin.permission.vo.BaseResponse newInstance() {
        return new mkcloudadmin.permission.vo.BaseResponse();
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
