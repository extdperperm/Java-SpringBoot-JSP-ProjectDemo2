package es.dsw.SpringBootProyectDemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "es.dsw")
public class SpringBootProyectDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProyectDemo2Application.class, args);
	}

}
