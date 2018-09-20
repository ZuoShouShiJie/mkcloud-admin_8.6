package mkcloudadmin.model.mkcloud.paging;


import mkcloudadmin.util.BasePage;

/**
 * @program: mkcloud-admin
 *
 * @description: 产品分页
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-08 19:30
 **/
public class FinancingProductPage extends BasePage{
    private String productName;
    private Integer isShow;
    private Integer category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
