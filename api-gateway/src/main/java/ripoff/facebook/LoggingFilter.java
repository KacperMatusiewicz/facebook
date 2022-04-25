package ripoff.facebook;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getRequest().getHeaders().toString());
        System.out.println(exchange.getRequest().toString());
        System.out.println(exchange.getRequest().getId().toString());
        System.out.println(exchange.getRequest().getCookies().toString());
        return chain.filter(exchange);
    }
}
