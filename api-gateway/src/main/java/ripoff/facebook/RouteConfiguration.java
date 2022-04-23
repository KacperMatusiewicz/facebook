package ripoff.facebook;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                    .host("localhost:8080")
                    .and()
                    .path("/api/v1/user/**")
                    .uri("lb://USER")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/post/**")
                        .uri("lb://POST")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/relation/**")
                        .uri("lb://RELATION")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/feed/**")
                        .uri("lb://FEED")
                )
                .route(r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/v1/notification/**")
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
