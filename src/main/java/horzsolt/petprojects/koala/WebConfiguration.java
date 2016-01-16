package horzsolt.petprojects.koala;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import horzsolt.petprojects.koala.util.HULocalDateFormatter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //localhost:8080/swagger-ui.html.
public class WebConfiguration extends WebMvcConfigurerAdapter {

	private static Logger logger = LogManager.getLogger(WebConfiguration.class.getName());
	
	//@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		
		logger.debug("----------------------------------------------------------------------------------------------------");
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        try {
			propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:koalaConfig.properties"));
		} catch (IOException e) {
			logger.error(e);
		}
        return propertySourcesPlaceholderConfigurer;		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatterForFieldType(LocalDate.class, new HULocalDateFormatter());
	}

}
