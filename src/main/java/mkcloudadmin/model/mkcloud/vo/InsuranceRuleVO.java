package mkcloudadmin.model.mkcloud.vo;/**
 * @program: mkcloud-admin
 * @description:
 * @author: MORUIHAI
 * @create: 2018-07-14 16:42
 **/

import java.math.BigDecimal;

/**
 * @program: mkcloud-admin
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-14 16:42
 **/
public class InsuranceRuleVO extends FinanceProductMainVO{
    private String totalBonus;

    private String terminalBonus;

    private String directBonus;

    private String indirectBonus;

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
        return "InsuranceRuleVO{" +
                "totalBonus='" + totalBonus + '\'' +
                ", terminalBonus='" + terminalBonus + '\'' +
                ", directBonus='" + directBonus + '\'' +
                ", indirectBonus='" + indirectBonus + '\'' +
                '}';
    }
}
