package horzsolt.petprojects.koala;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import horzsolt.petprojects.KoalaApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KoalaApplication.class)
@WebAppConfiguration
public class KoalaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
