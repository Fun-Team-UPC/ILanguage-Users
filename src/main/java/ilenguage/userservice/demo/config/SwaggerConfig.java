package ilenguage.userservice.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .tags(new Tag("Users", "Users"))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
    @Bean(name ="UserIlanguageOPEN-API")
    public OpenAPI iLanguageOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("User Aplication A P I")
                        .description("User microservice for Ilanguage Application implemmented with Spring Boot RESTful service and docummented using springdoc-openapi-ui 3.0"));

    }


}
