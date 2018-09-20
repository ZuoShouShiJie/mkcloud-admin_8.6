package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin
 *
 * @description: 贷款产品操作VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 15:40
 **/
public class LoanProductVO extends FinanceProductMainVO{
    private String mark1;

    private String mark2;

    private String amountScope;

    private String dateScope;

    private String totalBonus;

    private String terminalBonus;

    private String directBonus;

    private String indirectBonus;

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getAmountScope() {
        return amountScope;
    }

    public void setAmountScope(String amountScope) {
        this.amountScope = amountScope;
    }

    public String getDateScope() {
        return dateScope;
    }

    public void setDateScope(String dateScope) {
        this.dateScope = dateScope;
    }

    public String getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(BigDecimal totalBonus) {
        this.totalBonus = totalBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%";
    }

    public String getTerminalBonus() {
        return terminalBonus;
    }

    public void setTerminalBonus(BigDecimal terminalBonus) {
        this.terminalBonus = terminalBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%";
    }

    public String getDirectBonus() {
        return directBonus;
    }

    public void setDirectBonus(BigDecimal directBonus) {
        this.directBonus = directBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%";
    }

    public String getIndirectBonus() {
        return indirectBonus;
    }

    public void setIndirectBonus(BigDecimal indirectBonus) {
        this.indirectBonus = indirectBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()+"%";
    }

    @Override
    public String toString() {
        return "LoanProductVO{" +
                "mark1='" + mark1 + '\'' +
                ", mark2='" + mark2 + '\'' +
                ", amountScope='" + amountScope + '\'' +
                ", dateScope='" + dateScope + '\'' +
                ", totalBonus='" + totalBonus + '\'' +
                ", terminalBonus='" + terminalBonus + '\'' +
                ", directBonus='" + directBonus + '\'' +
                ", indirectBonus='" + indirectBonus + '\'' +
                '}';
    }
}
