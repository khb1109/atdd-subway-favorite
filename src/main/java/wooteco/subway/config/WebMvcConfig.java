package wooteco.subway.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import wooteco.subway.web.member.LoginMemberMethodArgumentResolver;
import wooteco.subway.web.member.interceptor.BearerAuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final BearerAuthInterceptor bearerAuthInterceptor;
	private final LoginMemberMethodArgumentResolver loginMemberArgumentResolver;

	public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor,
		LoginMemberMethodArgumentResolver loginMemberArgumentResolver) {
		this.bearerAuthInterceptor = bearerAuthInterceptor;
		this.loginMemberArgumentResolver = loginMemberArgumentResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(bearerAuthInterceptor)
			.addPathPatterns("/api/me", "/api/me/**", "/api/favorites", "/api/favorites/**")
			.excludePathPatterns("/api/me/login", "/api/me/sign_up");
	}

	@Override
	public void addArgumentResolvers(List argumentResolvers) {
		argumentResolvers.add(loginMemberArgumentResolver);
	}
}
