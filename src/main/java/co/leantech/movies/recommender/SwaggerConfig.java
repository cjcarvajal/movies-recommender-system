package co.leantech.movies.recommender;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author carlosj
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {

		Contact contact = new Contact("Carlos Carrvajal", "ing.carlosj@gmail.com",
				"https://leantechblog.wordpress.com/");
		ApiInfo apiInfo = new ApiInfo("Movies Recommender System",
				"Finds movies for a user according to his tastes",
				"1.0", StringUtils.EMPTY, contact, StringUtils.EMPTY, StringUtils.EMPTY);

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).forCodeGeneration(true)
				.useDefaultResponseMessages(true).enableUrlTemplating(true).select().build();
	}

}
