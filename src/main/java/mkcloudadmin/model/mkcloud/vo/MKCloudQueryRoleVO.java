package mkcloudadmin.model.mkcloud.vo;

import mkcloudadmin.model.mkcloud.po.MKCloudMenuInfo;
import mkcloudadmin.model.mkcloud.po.MKCloudRoleInfo;

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
public class MKCloudQueryRoleVO {
    private List<MKCloudRoleInfo> mkCloudRoleInfoList;
    private List<MKCloudRoleInfo> allRoleInfo;

    public List<MKCloudRoleInfo> getMkCloudRoleInfoList() {
        return mkCloudRoleInfoList;
    }

    public void setMkCloudRoleInfoList(List<MKCloudRoleInfo> mkCloudRoleInfoList) {
        this.mkCloudRoleInfoList = mkCloudRoleInfoList;
    }

    public List<MKCloudRoleInfo> getAllRoleInfo() {
        return allRoleInfo;
    }

    public void setAllRoleInfo(List<MKCloudRoleInfo> allRoleInfo) {
        this.allRoleInfo = allRoleInfo;
    }
}
