package com.security.springbootldaplearning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathBeanPostProcessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.Set;

@Configuration
public class LdapExternalSecurityConfig extends WebSecurityConfigurerAdapter {

 @Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userSearchFilter("(uid={0})")
                .userSearchBase("dc=springframework,dc=org")
                .groupSearchBase("ou=roles,dc=springframework,dc=org")
                .groupSearchFilter("cn={0}")
                .passwordEncoder(new BCryptPasswordEncoder())
                .contextSource()
                .url("ldap://localhost:389")
                .managerDn("cn=admin,dc=springframework,dc=org")
                .managerPassword("admin");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

/* @Bean
    public LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
        DefaultLdapAuthoritiesPopulator populi = new DefaultLdapAuthoritiesPopulator(contextSource(), "ou=roles") {

            private static final String ADMIN_ROLE = "ROLE_ADMIN";

            @Override
            public Set<GrantedAuthority> getGroupMembershipRoles(String userDn, String username) {
                Set<GrantedAuthority> groupMembershipRoles = super.getGroupMembershipRoles(userDn, username);

                boolean isMemberOfSpecificAdGroup = false;
                for (GrantedAuthority grantedAuthority : groupMembershipRoles) {

                    if (ADMIN_ROLE.equals(grantedAuthority.toString())) {
                        isMemberOfSpecificAdGroup = true;
                        break;
                    }
                }

                if (!isMemberOfSpecificAdGroup) {

                    throw new BadCredentialsException("User must be a member of " + ADMIN_ROLE);
                }
                return groupMembershipRoles;
            }
        };

        return populi;
    }*/


  @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        String ldapServer = this.ldapUrls;
        if (!ldapServer.endsWith("/")) ldapServer += "/";
        return new DefaultSpringSecurityContextSource(ldapServer + this.ldapBaseDn);
    }

}
