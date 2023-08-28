package com.nexters.keyme.global.config;

import com.nexters.keyme.global.common.annotation.ApiSecurityIgnore;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.annotation.SwaggerErrorCode;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String SWAGGER_AUTHORIZATION_SCHEME = "JWT 토큰";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(RequestUser.class)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(bearerAuthSecurityScheme()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            SwaggerErrorCode swaggerErrorCode =
                    handlerMethod.getMethodAnnotation(SwaggerErrorCode.class);
            if (swaggerErrorCode != null) {
                addErrorCode(operation, swaggerErrorCode.code(), swaggerErrorCode.description(), swaggerErrorCode.responseMessage());
            }
            return operation;
        };
    }

    private void addErrorCode(Operation operation, int code, String description, String responseMessage) {
        ApiResponses res = operation.getResponses();

        ApiResponse apiResponse = new ApiResponse();
        Content content = new Content();
        MediaType mediaType = new MediaType();
        Example example = new Example();

        example.setValue("example value");
        example.description("description");

        mediaType.setExample(example);

        content.addMediaType("application/json", mediaType);
        apiResponse.setContent(content);

        res.addApiResponse(description, apiResponse);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("v0.0.1")
                .title("Keyme API")
                .description("Keyme API Docs입니다.")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(this::isSecurityOperation)
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Arrays.asList(new SecurityReference(SWAGGER_AUTHORIZATION_SCHEME, authorizationScopes));
    }

    private HttpAuthenticationScheme bearerAuthSecurityScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER
                .name(SWAGGER_AUTHORIZATION_SCHEME).build();
    }

    private boolean isSecurityOperation(OperationContext operationContext) {
        return operationContext.findAnnotation(ApiSecurityIgnore.class).isEmpty();
    }
}