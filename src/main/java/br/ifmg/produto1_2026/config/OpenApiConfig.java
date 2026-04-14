package br.ifmg.produto1_2026.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info()
                        .title("ProdutoS API")
                        .description("API para sistema de vendas")
                        .version("1.0")
                        .summary("Loja do bairro")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.lojinhateste.com")
                        )
        );
    }
}