package mkcloudadmin.model.mkcloud.vo;

import java.util.List;

/**
 * Created by daixiaohu on 2018/8/31.
 */
public class MKCloudMenuAllListVO {
    private List<MKCloudMenuListVO> mkCloudMenuListVOS;
    private List<MKCloudMenuListVO> allMenuFirstListVo;
    private List<MKCloudMenuListVO> allMenuSecondListVo;

    public List<MKCloudMenuListVO> getMkCloudMenuListVOS() {
        return mkCloudMenuListVOS;
    }

    public void setMkCloudMenuListVOS(List<MKCloudMenuListVO> mkCloudMenuListVOS) {
        this.mkCloudMenuListVOS = mkCloudMenuListVOS;
    }

    public List<MKCloudMenuListVO> getAllMenuFirstListVo() {
        return allMenuFirstListVo;
    }

    public void setAllMenuFirstListVo(List<MKCloudMenuListVO> allMenuFirstListVo) {
        this.allMenuFirstListVo = allMenuFirstListVo;
    }

    public List<MKCloudMenuListVO> getAllMenuSecondListVo() {
        return allMenuSecondListVo;
    }

    public void setAllMenuSecondListVo(List<MKCloudMenuListVO> allMenuSecondListVo) {
        this.allMenuSecondListVo = allMenuSecondListVo;
    }
}
