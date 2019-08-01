package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"spring"}) // spring 패키지 중 @Component 있는 경우 모두 객체화
public class AppCtx {
}
