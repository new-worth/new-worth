package cn.worth.common.utils;

import cn.worth.common.pojo.UserVO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @Author: chenxiaoqing9
 * @Date: Created in 2019/3/19
 * @Description: 用户相关工具类
 * @Modified by:
 */
@Slf4j
public class UserUtils {

    public static UserVO getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            Object principal = authentication.getPrincipal();

            UserVO userVO = JSONObject.parseObject(JSONObject.toJSONString(principal), UserVO.class);
            return userVO;
        }
        return null;
    }
}
