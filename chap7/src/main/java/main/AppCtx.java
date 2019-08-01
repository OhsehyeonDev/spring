package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"annotation"})
@EnableAspectJAutoProxy
public class AppCtx {
	@Bean
	public MemberService2 memberService2() {
		return new MemberService2();
	}
}
