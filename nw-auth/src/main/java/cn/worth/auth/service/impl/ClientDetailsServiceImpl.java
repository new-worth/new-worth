package cn.worth.auth.service.impl;

import cn.worth.auth.service.OauthClientDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 自定义客户端认证
 * @Author: chenxiaoqing9
 * @Date: Created in 2019/3/11
 * @Description:
 * @Modified by:
 */
@Service("clientDetailsService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsService clientDetailsService;

    private static final Logger log = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails client = null;
        log.info("clientDetailsService accepted clientId is ============={}", clientId);
        //这里可以改为查询数据库
        if ("client".equals(clientId)) {
            client = new BaseClientDetails();
            client.setClientId(clientId);
            client.setClientSecret("{noop}123456");
            //client.setResourceIds(Arrays.asList("order"));
            client.setAuthorizedGrantTypes(Arrays.asList("authorization_code",
                    "client_credentials", "refresh_token", "password", "implicit"));
            //不同的client可以通过 一个scope 对应 权限集
            client.setScope(Arrays.asList("all", "select"));
            client.setAuthorities(AuthorityUtils.createAuthorityList("admin_role"));
            client.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); //1天
            client.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); //1天
//            Set<String> uris = new HashSet<>();
//            uris.add("http://www.baidu.com");
//            client.setRegisteredRedirectUri(uris);
        }
        if (client == null) {
            throw new NoSuchClientException("No client width requested id: " + clientId);
        }
        return client;
    }
}
