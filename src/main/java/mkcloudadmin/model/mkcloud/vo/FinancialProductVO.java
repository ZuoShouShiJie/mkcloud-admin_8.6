package mkcloudadmin.model.mkcloud.vo;

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 10:45
 **/
public class FinancialProductVO extends FinanceProductMainVO{

    private String mark;

    private String aveRevenue;

    private String productBackground;

    private String grade;

    private String backgroundStrength;

    private String riskControl;

    private String operationCapability;

    private String startAmount;

    private String startPeriod;

    private String rebackName;

    private String rebackValue;

    private String totalReturn;

    private String totalBonus;

    private String terminalBonus;

    private String directBonus;

    private String indirectBonus;
    private String cashbackRule;

    public String getCashbackRule() {
        return cashbackRule;
    }

    public void setCashbackRule(String cashbackRule) {
        this.cashbackRule = cashbackRule;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getAveRevenue() {
        return aveRevenue;
    }

    public void setAveRevenue(String aveRevenue) {
        this.aveRevenue = aveRevenue == null ? null : aveRevenue.trim();
    }

    public String getProductBackground() {
        return productBackground;
    }

    public void setProductBackground(String productBackground) {
        this.productBackground = productBackground == null ? null : productBackground.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getBackgroundStrength() {
        return backgroundStrength;
    }

    public void setBackgroundStrength(String backgroundStrength) {
        this.backgroundStrength = backgroundStrength == null ? null : backgroundStrength.trim();
    }

    public String getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(String riskControl) {
        this.riskControl = riskControl == null ? null : riskControl.trim();
    }

    public String getOperationCapability() {
        return operationCapability;
    }

    public void setOperationCapability(String operationCapability) {
        this.operationCapability = operationCapability == null ? null : operationCapability.trim();
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount == null ? null : startAmount.trim();
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod == null ? null : startPeriod.trim();
    }

    public String getRebackName() {
        return rebackName;
    }

    public void setRebackName(String rebackName) {
        this.rebackName = rebackName == null ? null : rebackName.trim();
    }

    public String getRebackValue() {
        return rebackValue;
    }

    public void setRebackValue(String rebackValue) {
        this.rebackValue = rebackValue == null ? null : rebackValue.trim();
    }

    public String getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(String totalReturn) {
        this.totalReturn = totalReturn == null ? null : totalReturn.trim();
    }

    @Override
    public String toString() {
        return "FinancialProductVO{" +
                "mark='" + mark + '\'' +
                ", aveRevenue='" + aveRevenue + '\'' +
                ", productBackground='" + productBackground + '\'' +
                ", grade='" + grade + '\'' +
                ", backgroundStrength='" + backgroundStrength + '\'' +
                ", riskControl='" + riskControl + '\'' +
                ", operationCapability='" + operationCapability + '\'' +
                ", startAmount='" + startAmount + '\'' +
                ", startPeriod='" + startPeriod + '\'' +
                ", rebackName='" + rebackName + '\'' +
                ", rebackValue='" + rebackValue + '\'' +
                ", totalReturn='" + totalReturn + '\'' +
                ", totalBonus='" + totalBonus + '\'' +
                ", terminalBonus='" + terminalBonus + '\'' +
                ", directBonus='" + directBonus + '\'' +
                ", indirectBonus='" + indirectBonus + '\'' +
                ", cashbackRule='" + cashbackRule + '\'' +
                '}';
    }
}
