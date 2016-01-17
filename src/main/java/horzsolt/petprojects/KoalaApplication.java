package horzsolt.petprojects;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class KoalaApplication {
	
	public static ApplicationContext context;

    @Component
    public static class KoalaContext {
 
    	@Value("${mysql_connection}")
        private String jdbc_url;
    	@Value("${koala.DAY_HOME}")
        private String DAY_HOME;
    	@Value("${koala.ip}")
        private String ipAddress;
    	@Value("${koala.port}")
        private String port;
    	@Value("${koala.username}")
        private String userName;
    	@Value("${koala.password}")	
        private String password;
    	@Value("${koala.serializationroot}")
    	public String serializationRoot;
    	
        public String getSerializationRoot() {
			return serializationRoot;
		}

		public String getIpAddress() {
            return ipAddress;
        }

        public String getPort() {
            return port;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public String getDAY_HOME() {
			return DAY_HOME;
		}

		public String getJdbc_Url() {
            return jdbc_url;
        }
    }
    
	public static void main(String[] args) {
		context = SpringApplication.run(KoalaApplication.class, args);
		
		String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        
        for (String beanName : beanNames) {
            //System.out.println(beanName);
        }
	}
}
