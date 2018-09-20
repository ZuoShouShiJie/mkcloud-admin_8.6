package mkcloudadmin.model.mkcloud.vo;

import mkcloudadmin.model.mkcloud.po.MKCloudMenuInfo;

import java.util.List;

/**
 * @program: mkcloud-admin_8.6
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-09 18:27
 **/
public class MKCloudQueryUserMenuVO {
    private  List<MKCloudMenuInfo> mkCloudMenuInfos;
    private List<MKCloudMenuInfo> allMenuInfo;

    public List<MKCloudMenuInfo> getMkCloudMenuInfos() {
        return mkCloudMenuInfos;
    }

    public void setMkCloudMenuInfos(List<MKCloudMenuInfo> mkCloudMenuInfos) {
        this.mkCloudMenuInfos = mkCloudMenuInfos;
    }

    public List<MKCloudMenuInfo> getAllMenuInfo() {
        return allMenuInfo;
    }

    public void setAllMenuInfo(List<MKCloudMenuInfo> allMenuInfo) {
        this.allMenuInfo = allMenuInfo;
    }
}
