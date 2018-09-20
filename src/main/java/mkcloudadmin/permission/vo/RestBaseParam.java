//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class RestBaseParam {
    private int connectTimeout;
    private int readTimeout;

    public RestBaseParam() {
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
