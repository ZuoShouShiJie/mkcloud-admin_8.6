package mkcloudadmin.model.mkcloud.vo;

/**
 * Created by Administrator on 2018/8/27.
 */
public class IncomeCountVO {

    private String totalCommission;
    private String naturalTotal;
    private String recommendTotal;

    private String promotersTotal;
    private String promotersProfit;
    private String totalProfit;

    public String getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(String totalCommission) {
        this.totalCommission = totalCommission;
    }

    public String getNaturalTotal() {
        return naturalTotal;
    }

    public void setNaturalTotal(String naturalTotal) {
        this.naturalTotal = naturalTotal;
    }

    public String getRecommendTotal() {
        return recommendTotal;
    }

    public void setRecommendTotal(String recommendTotal) {
        this.recommendTotal = recommendTotal;
    }

    public String getPromotersTotal() {
        return promotersTotal;
    }

    public void setPromotersTotal(String promotersTotal) {
        this.promotersTotal = promotersTotal;
    }

    public String getPromotersProfit() {
        return promotersProfit;
    }

    public void setPromotersProfit(String promotersProfit) {
        this.promotersProfit = promotersProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }
}
