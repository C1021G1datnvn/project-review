package gg.fresher.demo.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @created Mar 8, 2023
 *
 */
@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("hoiana-api-v1")
                .pathsToMatch("/api/**")
                .build();
    }

    // Enable when successfully configuration JWT security
//    @Bean
//    OpenApiCustomiser adminApiCustomizer() {
//        return openApi -> {
//            openApi.addSecurityItem(new SecurityRequirement().addList("token"));
//            
//            openApi.getComponents().addSecuritySchemes("token", new SecurityScheme()
//                    .type(SecurityScheme.Type.HTTP)
//                    .in(SecurityScheme.In.HEADER)
//                    .scheme("bearer")
//                    .bearerFormat("jwt")
//                    .name("Authorization"));
//        };
//    }
}
