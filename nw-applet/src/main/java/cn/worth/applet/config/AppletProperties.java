package cn.worth.applet.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wx")
public class AppletProperties {

    private String secret;
    private String appId;
    private String mchId;
    private String paySignKey;
    private String certName;
    private String getCode;
    private String notifyUrl;
    private String orderQuery;
    private String refundUrl;
    private String refundQueryUrl;
    private String tradeType;
    private String uniformOrder;
    private String userMessage;
    private String jsCode2sessionUrl;
    private  String templateId ;
}