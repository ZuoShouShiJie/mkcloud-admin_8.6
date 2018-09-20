package mkcloudadmin.model.mkcloud.po;

import java.math.BigDecimal;

/**
 * @author yaolei
 * @Title: FinanceCalculateMoney
 * @ProjectName mkcloud-admin
 * @Description: 计算
 * @date 2018/7/13上午10:25
 */
public class FinanceCalculateMoney {

    /**计算状态**/
    private String status;
    /**错误信息**/
    private String errorMsg;
    /**本人userId**/
    private Long terminalId;
    /**本人分润**/
    private BigDecimal terminalMoney;
    /**本人手机号**/
    private String terminalPhone;
    /**本人姓名**/
    private String terminalName;

    /**父级id**/
    private Long parentId;
    /**父级分润**/
    private BigDecimal parentMoney;
    /**父级手机号**/
    private String parentPhone;
    /**父级名字**/
    private String parentName;

    /**上上级id**/
    private Long grandParentId;
    /**上上级分润**/
    private BigDecimal grandParentMoney;
    /**上上级手机号**/
    private String grandParenPhone;
    /**上上级名字**/
    private String grandParentName;

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getGrandParenPhone() {
        return grandParenPhone;
    }

    public void setGrandParenPhone(String grandParenPhone) {
        this.grandParenPhone = grandParenPhone;
    }

    public String getGrandParentName() {
        return grandParentName;
    }

    public void setGrandParentName(String grandParentName) {
        this.grandParentName = grandParentName;
    }

    public String getTerminalPhone() {
        return terminalPhone;
    }

    public void setTerminalPhone(String terminalPhone) {
        this.terminalPhone = terminalPhone;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getGrandParentPhone() {
        return grandParentPhone;
    }

    public void setGrandParentPhone(String grandParentPhone) {
        this.grandParentPhone = grandParentPhone;
    }

    /**上上级手机号**/
    private String grandParentPhone;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public BigDecimal getTerminalMoney() {
        return terminalMoney;
    }

    public void setTerminalMoney(BigDecimal terminalMoney) {
        this.terminalMoney = terminalMoney;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getParentMoney() {
        return parentMoney;
    }

    public void setParentMoney(BigDecimal parentMoney) {
        this.parentMoney = parentMoney;
    }

    public Long getGrandParentId() {
        return grandParentId;
    }

    public void setGrandParentId(Long grandParentId) {
        this.grandParentId = grandParentId;
    }

    public BigDecimal getGrandParentMoney() {
        return grandParentMoney;
    }

    public void setGrandParentMoney(BigDecimal grandParentMoney) {
        this.grandParentMoney = grandParentMoney;
    }
}
