package cl.falabella.msproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.falabella.msproduct.infrastructure.Setting;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private Setting setting;
	
	@Bean
    public Docket swagger() {
        String packageName = getClass().getPackage().getName();
        packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageName))
                .build()
                .apiInfo(getApiInformation());
    }


    private ApiInfo getApiInformation(){

        return new ApiInfoBuilder()
        .title("[REST API] [" + setting.getProjectName() + "]")
        .description("Product service test")
        .version(setting.getVersion())
        .license("Falabella product test evaluation")
        .build();
        
    }
  
}