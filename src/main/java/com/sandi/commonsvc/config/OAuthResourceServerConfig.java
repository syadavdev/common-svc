package com.sandi.commonsvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected Environment env;

    @Autowired
    public void configure(final HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests().anyRequest().permitAll();
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public ResourceServerTokenServices defaultTokenServices(){
        DefaultTokenServices resourceServerTokenServices = new DefaultTokenServices();
        resourceServerTokenServices.setTokenEnhancer(accessTokenConverter());
        resourceServerTokenServices.setTokenStore(tokenStore());
        return resourceServerTokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(env.getProperty("oauth2.jwt.public.key"));
        return converter;
    }

}
