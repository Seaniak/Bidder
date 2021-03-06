package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaVueApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(JavaVueApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING SPRING SERVER");
		SpringApplication.run(JavaVueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//	future:	add loop to execute all beans automatically
		LOG.info("EXECUTING: command line runner");
	}
}
