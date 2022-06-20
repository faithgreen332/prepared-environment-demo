package com.funpay.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.funpay.model.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author alen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TransferReq extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "\\'merchantID\\' is required")
    private int merchantID;
    @NotNull(message = "\\'businessID\\' is required")
    private int businessID;

    private String clientID;

    private String timestamp;

    // TODO 这里应该要校验一下格式
    private String amount;

    private String currency;

    private String bankLocation;

    private String orderNo;

    private String returnUrl;

    @NotNull(message = "bankNo error")
    private int bankNo;

    /**
     * firstName-middleName-LastName
     */
    @Pattern(regexp = "[a-zA-Z]{1,50}-[a-zA-Z_ ]{0,50}-[a-zA-Z]{1,50}", message = "Account name must use the format [firstName-middleName-lastName]")
    private String accountName;

    private String accountNo;

    @Pattern(regexp = "^9\\d{9}$|^09\\d{9}$|^639\\d{9}$", message = "The phoneNumber must beginning with 9 or 09 or 639")
    private String phoneNumber;

    private int accountType;

    private String remark;

    private int bankBranchNo;

    private String version;

    /**
     * 支付方法，对应表 t_business_charge 的 business_code,TBusinessCharge 的 businessCode
     */
    private int payWay;

    /**
     * 代付产品类型，BANKRT：银行实时BANKNRT：银行非实时 GCASH PAYMAYA GRABPAY
     */
    private String productType;
    /**
     * 国家，菲律宾应为PHP
     */
    @NotBlank(message = "country can not be empty")
    private String country;
    /**
     * 省
     */
    @NotBlank(message = "province can not be empty")
    private String province;
    /**
     * 城市
     */
    @NotBlank(message = "city can not be empty")
    private String city;
    /**
     * 邮编
     */
    @NotBlank(message = "zipCode can not be empty")
    private String zipCode;
    /**
     * 地址1
     */

    @NotBlank(message = "line1 can not be empty")
    private String line1;
    /**
     * 地址2
     */
    @NotBlank(message = "line2 can not be empty")
    private String line2;

    /**
     * 身份类型
     */
    @NotNull(message = "IDType not null")
    @Pattern(regexp = "SSS|TIN|Passport|PhiHealth|Driver's license|Voter's ID|UMID|Postal ID|PRC ID|GSIS ID|Student's ID|Pag IBIG|National ID", message = "IDType does not match")
    private String IDType;
    /**
     * 卡号
     */
    @NotNull(message = "IDNo is required")
    private String IDNo;

    @JsonProperty("IDType")
    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    @JsonProperty("IDNo")
    public String getIDNo() {
        return IDNo;
    }

    public void setIDNo(String iDNo) {
        IDNo = iDNo;
    }


    /**
     * referenceNo
     */
    private String referenceNo;

    /**
     * 是否异步处理
     */
    private int isAsync;

    /**
     * 系统唯一交易号，uc 生成订单的时候生成，加这个字段时为了用唯一索引查询
     */
    private String tradeNo;


    public TransferReq() {
        super();
    }
}
