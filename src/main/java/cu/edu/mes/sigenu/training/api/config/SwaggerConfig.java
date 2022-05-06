package cu.edu.mes.sigenu.training.api.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private Environment env;
	
	@Autowired
    private ServletContext servletContext; 
	
	
	@Bean
	public Docket homologationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cu.edu.mes.sigenu.training.api"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
			    .securitySchemes(Arrays.asList(apiKey()));
	}
	
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private Set<String> getProtocols() {
		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		protocols.add("https");
		return protocols;
	}

	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(env.getProperty("cu.edu.mes.sigenu.training.swagger.title"))
				.description(env.getProperty("cu.edu.mes.sigenu.training.swagger.description"))
				.version(env.getProperty("cu.edu.mes.sigenu.training.swagger.version"))
				.build();
	}
}
