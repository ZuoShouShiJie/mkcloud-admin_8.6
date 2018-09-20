package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin
 *
 * @description: 信用卡操作VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 15:00
 **/
public class CreditCardProductVO extends FinanceProductMainVO{
    private String passRate;

    private String detailPageUrl;
    private String rebackCashDesc;
    private String totalBonus;

    private String terminalBonus;

    private String directBonus;

    private String indirectBonus;

    public String getRebackCashDesc() {
        return rebackCashDesc;
    }

    public void setRebackCashDesc(String rebackCashDesc) {
        this.rebackCashDesc = rebackCashDesc;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getDetailPageUrl() {
        return detailPageUrl;
    }

    public void setDetailPageUrl(String detailPageUrl) {
        this.detailPageUrl = detailPageUrl;
    }

    public String getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(BigDecimal totalBonus) {
        this.totalBonus = totalBonus.stripTrailingZeros().toPlainString();
    }

    public String getTerminalBonus() {
        return terminalBonus;
    }

    public void setTerminalBonus(BigDecimal terminalBonus) {
        this.terminalBonus = terminalBonus.stripTrailingZeros().toPlainString();
    }

    public String getDirectBonus() {
        return directBonus;
    }

    public void setDirectBonus(BigDecimal directBonus) {
        this.directBonus = directBonus.stripTrailingZeros().toPlainString();
    }

    public String getIndirectBonus() {
        return indirectBonus;
    }

    public void setIndirectBonus(BigDecimal indirectBonus) {
        this.indirectBonus = indirectBonus.stripTrailingZeros().toPlainString();
    }

    @Override
    public String toString() {
        return "CreditCardProductVO{" +
                "passRate='" + passRate + '\'' +
                ", detailPageUrl='" + detailPageUrl + '\'' +
                ", rebackCashDesc='" + rebackCashDesc + '\'' +
                ", totalBonus='" + totalBonus + '\'' +
                ", terminalBonus='" + terminalBonus + '\'' +
                ", directBonus='" + directBonus + '\'' +
                ", indirectBonus='" + indirectBonus + '\'' +
                '}';
    }
}
