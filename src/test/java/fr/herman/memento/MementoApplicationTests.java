package fr.herman.memento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import fr.herman.memento.MementoApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MementoApplication.class)
@WebAppConfiguration
public class MementoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
