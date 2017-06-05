/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.wangxj.authority.plugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Logs a principal out.
 * <p>
 * Polls a series of {@link LogoutHandler}s. The handlers should be specified in the order
 * they are required. Generally you will want to call logout handlers
 * <code>TokenBasedRememberMeServices</code> and <code>SecurityContextLogoutHandler</code>
 * (in that order).
 * <p>
 * After logout, a redirect will be performed to the URL determined by either the
 * configured <tt>LogoutSuccessHandler</tt> or the <tt>logoutSuccessUrl</tt>, depending on
 * which constructor was used.
 *
 * @author Ben Alex
 */
public class WangXJLogoutFilter extends GenericFilterBean {

	// ~ Instance fields
	// ================================================================================================

	private RequestMatcher logoutRequestMatcher;

	private final List<LogoutHandler> handlers;
	private final LogoutSuccessHandler logoutSuccessHandler;
	
	private final String logoutSuccUrl;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructor which takes a <tt>LogoutSuccessHandler</tt> instance to determine the
	 * target destination after logging out. The list of <tt>LogoutHandler</tt>s are
	 * intended to perform the actual logout functionality (such as clearing the security
	 * context, invalidating the session, etc.).
	 */
	public WangXJLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
			LogoutHandler... handlers) {
		Assert.notEmpty(handlers, "LogoutHandlers are required");
		this.handlers = Arrays.asList(handlers);
		Assert.notNull(logoutSuccessHandler, "logoutSuccessHandler cannot be null");
		this.logoutSuccessHandler = logoutSuccessHandler;
		setFilterProcessesUrl("/logout");
		this.logoutSuccUrl = "";
	}

	public WangXJLogoutFilter(String logoutSuccessUrl, LogoutHandler... handlers) {
		Assert.notEmpty(handlers, "LogoutHandlers are required");
		this.handlers = Arrays.asList(handlers);
		Assert.isTrue(
				!StringUtils.hasLength(logoutSuccessUrl)
						|| UrlUtils.isValidRedirectUrl(logoutSuccessUrl),
				logoutSuccessUrl + " isn't a valid redirect URL");
		SimpleUrlLogoutSuccessHandler urlLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
		
		this.logoutSuccUrl = logoutSuccessUrl;
		
		logoutSuccessHandler = urlLogoutSuccessHandler;
		setFilterProcessesUrl("/logout");
	}

	// ~ Methods
	// ========================================================================================================

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (requiresLogout(request, response)) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (logger.isDebugEnabled()) {
				logger.debug("Logging out user '" + auth
						+ "' and transferring to logout destination");
			}

			for (LogoutHandler handler : handlers) {
				handler.logout(request, response, auth);
			}
			
			String url = request.getRequestURL().toString();
			SimpleUrlLogoutSuccessHandler logoutHander =  (SimpleUrlLogoutSuccessHandler) logoutSuccessHandler;
			String defaultTargetUrl = url.substring(0, url.length() - "j_spring_cas_security_logout".length());
			defaultTargetUrl = "?service="+defaultTargetUrl;
			logoutHander.setDefaultTargetUrl(logoutSuccUrl + defaultTargetUrl +"login/cas");
			 
			logoutSuccessHandler.onLogoutSuccess(request, response, auth);

			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * Allow subclasses to modify when a logout should take place.
	 *
	 * @param request the request
	 * @param response the response
	 *
	 * @return <code>true</code> if logout should occur, <code>false</code> otherwise
	 */
	protected boolean requiresLogout(HttpServletRequest request,
			HttpServletResponse response) {
		return logoutRequestMatcher.matches(request);
	}

	public void setLogoutRequestMatcher(RequestMatcher logoutRequestMatcher) {
		Assert.notNull(logoutRequestMatcher, "logoutRequestMatcher cannot be null");
		this.logoutRequestMatcher = logoutRequestMatcher;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.logoutRequestMatcher = new AntPathRequestMatcher(filterProcessesUrl);
	}
}
