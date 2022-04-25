package ripoff.facebook;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import ripoff.facebook.clients.authentication.AuthClient;

import java.beans.beancontext.BeanContext;

@Configuration
@RequiredArgsConstructor
public class RouteConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter, LoggingFilter loggingFilter) {
        return builder.routes()
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/auth/login")
                        .uri("lb://AUTHENTICATION")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/auth/logout")
                        .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                        .uri("lb://AUTHENTICATION")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .method(HttpMethod.POST)
                        .and()
                        .path("/api/v1/user/**")
                        .uri("lb://USER")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/user/activate/**")
                        .uri("lb://USER")
                )
                .route(r -> r
                    .host("localhost:8080")
                    .and()
                    .path("/api/v1/user/**")
                    .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                    .uri("lb://USER")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/post/**")
                        .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                        .uri("lb://POST")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/relation/**")
                        .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                        .uri("lb://RELATION")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/feed/**")
                        .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                        .uri("lb://FEED")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/notification/**")
                        .filters(f -> f.filter(authenticationFilter).filter(loggingFilter))
                        .uri("lb://NOTIFICATION")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/**")
                        .uri("http://localhost:8115") //TODO: on production it should be "lb://WEB-SERVER" so it uses SD.
                )
                .build();
    }
}
