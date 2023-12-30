package com.example.account.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String SLASH = "/";

    private static final String ACC_PATH = "accApi";

    @Value("${acc-api.version}")
    private String apiVersion;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("ADMIN")
                .and()
                .withUser("samin")
                .password(passwordEncoder().encode("samin"))
                .authorities("ACCOUNT", "TRANSACTION")
                .and()
                .withUser("farzane")
                .password(passwordEncoder().encode("farzane"))
                .authorities("FACILITY", "PERSON");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(SLASH + ACC_PATH + SLASH).permitAll()

                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/account/openAnAccount").hasIpAddress("ACCOUNT")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/account/getAllAccountInfos").hasIpAddress("ACCOUNT")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/account/getAccountInfoByAccountNumber").hasIpAddress("ACCOUNT")

                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/facility/getFacility").hasIpAddress("FACILITY")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/facility/conditionForFacility").hasIpAddress("FACILITY")

                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/getAllTransactions").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/getOpenAccountTransactions").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/getTransactionsBySourceAccountNumber").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/getTransactionsByDestAccountNumber").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/getTransactionsByTransferDate").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/internalTransfer").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/cashDeposit").hasIpAddress("TRANSACTION")
                .antMatchers(SLASH + ACC_PATH + SLASH + apiVersion + "/transaction/cashWithdraw").hasIpAddress("TRANSACTION")

//                .anyRequest().denyAll()
                .and()
                .cors().disable()
                .csrf().disable()
                .httpBasic();

        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
