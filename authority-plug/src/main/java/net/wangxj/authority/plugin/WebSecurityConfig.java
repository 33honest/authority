package net.wangxj.authority.plugin;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * Spring Security configs.
 *
 * @author marco
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // CAS单点登录服务地址
	@Value("${cas.server.login.url}")
    private String casServerLoginUrl;
	@Value("${platform.sign}")
    private String platformSign;
	@Value("${authority.service.rest.url}")
    private String authorityServiceRestUrl;
	@Value("${cas.server.logout.url}")
    private String casServerLogoutUrl;
	@Value("${spring.security.logout.url}")
    private String springSecurityLogoutUrl;
	@Value("${denied.page}")
    private String deniedPage;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /**
     * Spring Security 基本配置
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity // 无权访问时转向页面
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().addFilterBefore(requestLogoutFilter(), LogoutFilter.class)
                .addFilterBefore(singleLogoutFilter(),CasAuthenticationFilter.class)
                .addFilterBefore(userSecurityInterceptorFilter(), FilterSecurityInterceptor.class)
                .addFilterBefore(casAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandler())
                .authenticationEntryPoint(getCasAuthenticationEntryPoint())
                .and()
                .logout().permitAll()
                .and().headers().frameOptions().sameOrigin();
    }
    /**
     * 配置CAS登录页面
     */
    public CasAuthenticationEntryPoint getCasAuthenticationEntryPoint() {
        WangXJCasAuthenticationEntryPoint point = new WangXJCasAuthenticationEntryPoint();
        point.setLoginUrl(casServerLoginUrl);
        point.setServiceProperties(serviceProperties());
        return point;
    }
    /**
     * 认证过滤器
     */
    public WangXJCasAuthenticationFilter casAuthenticationFilter() throws Exception {
        WangXJCasAuthenticationFilter filter = new WangXJCasAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
    public WangXJLogoutFilter requestLogoutFilter() {
       WangXJLogoutFilter logoutFilter = new WangXJLogoutFilter(casServerLogoutUrl, new SecurityContextLogoutHandler());
       logoutFilter.setFilterProcessesUrl(springSecurityLogoutUrl);
        return logoutFilter;
    }
    public SingleSignOutFilter singleLogoutFilter() {
        
        return new SingleSignOutFilter();
    }
    @SuppressWarnings("unchecked")
	@Bean
    public WangXJCasAuthenticationProvider casAuthenticationProvider() {
        WangXJCasAuthenticationProvider provider = new WangXJCasAuthenticationProvider();
        Cas20ServiceTicketValidator cas20ServiceTicket = new Cas20ServiceTicketValidator(casServerLoginUrl);
        cas20ServiceTicket.setEncoding("UTF-8");
        provider.setTicketValidator(cas20ServiceTicket);
        provider.setServiceProperties(serviceProperties());
        provider.setKey("an_id_for_this_auth_provider_only");
        provider.setAuthenticationUserDetailsService(userDetailsByNameServiceWrapper());
        return provider;
    }
    
    private ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("http://baidu.com");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }
    
    
    /**
     * 当CAS认证成功时, Spring Security会自动调用此类对用户进行授权
     */
    @SuppressWarnings("rawtypes")
	private UserDetailsByNameServiceWrapper userDetailsByNameServiceWrapper() {
        UserDetailsByNameServiceWrapper wrapper = new UserDetailsByNameServiceWrapper();
        userDetailsService.setPlatform_sign(platformSign);
        userDetailsService.setAuthority_service_url(authorityServiceRestUrl);
        wrapper.setUserDetailsService(userDetailsService);
        return wrapper;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }
    
    private UserSecurityInterceptorFilter userSecurityInterceptorFilter() throws Exception{
    	UserSecurityInterceptorFilter securityInterceptorFilter = new UserSecurityInterceptorFilter();
    	securityInterceptorFilter.setAuthenticationManager(authenticationManager());
    	securityInterceptorFilter.setAccessDecisionManager(new UserAccessDecisionManager());
    	securityInterceptorFilter.setSecurityMetadataSource(userSecurityMetaDataSource());
    	return securityInterceptorFilter;
    }
    
    public UserSecurityMetadataSource userSecurityMetaDataSource(){
    	UserSecurityMetadataSource securityMetaDataSource = new UserSecurityMetadataSource();
    	securityMetaDataSource.setPlatform_sign(platformSign);
    	securityMetaDataSource.setAuthority_service_url(authorityServiceRestUrl);
    	return securityMetaDataSource;
    }
    
    public UserAccessDeniedHandler userAccessDeniedHandler(){
    	UserAccessDeniedHandler accessDeniedHandler = new UserAccessDeniedHandler();
    	accessDeniedHandler.setErrorPage(deniedPage);
    	return accessDeniedHandler;
    }
    
    @Bean
    public DefaultWebInvocationPrivilegeEvaluator webInvocationPrivilege() throws Exception{
    	return  new DefaultWebInvocationPrivilegeEvaluator(userSecurityInterceptorFilter());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception{
    	web.securityInterceptor(userSecurityInterceptorFilter());
    	web.ignoring().antMatchers("/js/**", "/css/**", 
    		    "/imgs/**","/fonts/**","/vimage*",
    		    "/server-error/**","/favicon.ico","/image/**","/img/**","/403/**");
    }
    
}