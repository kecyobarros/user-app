package br.com.kecyo.userapp.config.springfox;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Configuration
@EnableSwagger2
public class SpringfoxConfig {

    @Bean
    public Docket gatewayApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, defaultErrorsMessage())
                .globalResponseMessage(RequestMethod.PUT, defaultErrorsMessage())
                .globalResponseMessage(RequestMethod.GET, defaultErrorsMessage())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> defaultErrorsMessage() {
        return Lists.newArrayList(
                new ResponseMessageBuilder().code(NOT_FOUND.value())
                        .message("Data not found").build(),
                new ResponseMessageBuilder().code(INTERNAL_SERVER_ERROR.value())
                        .message("Internal error on user api").build(),
                new ResponseMessageBuilder().code(BAD_REQUEST.value())
                        .message("Invalid data ").build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User API")
                .description("API provides information users.")
                .version("1.0")
                .contact(new Contact("Kecyo Barros", null, "kecyobarros@gmail.com"))
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

}
