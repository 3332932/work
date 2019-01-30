package com.cn.jwt.configuration;

import com.cn.jwt.JwtToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yshh44
 */
@Configuration
public class WorkConfiguration {
   @Bean
   public JwtToken jwtToken(){
       JwtToken jwtToken = new JwtToken();
       jwtToken.setSecret("d432@S#%$*^#&^&$^*&^#*&^&^*&^%$#");
       return jwtToken;
   }

}
