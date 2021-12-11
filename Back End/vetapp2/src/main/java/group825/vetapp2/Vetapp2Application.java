package group825.vetapp2;

import group825.vetapp2.database.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vetapp2Application {

	public static void main(String[] args) {
		DatabaseConnection.initialize();
		SpringApplication.run(Vetapp2Application.class, args);
//		DatabaseConnection.close();
	}

}
