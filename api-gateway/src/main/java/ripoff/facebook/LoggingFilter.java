package ripoff.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(
                "Request " +
                (
                    exchange.getRequest().getCookies().containsKey("JSESSIONID") ?
                            "authenticated " : "not authenticated "
                ) +
                "to path: " + exchange.getRequest().getPath()
        );
        return chain.filter(exchange);
    }
}
