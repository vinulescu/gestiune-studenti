package ro.facultate.gestiune_studenti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.facultate.gestiune_studenti.model.Utilizator;
import ro.facultate.gestiune_studenti.repository.UtilizatorRepository;

@SpringBootApplication
public class GestiuneStudentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiuneStudentiApplication.class, args);
	}

	// Acest cod rulează automat de fiecare dată când pornești aplicația
	@Bean
	CommandLineRunner initDatabase(UtilizatorRepository utilizatorRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Verificăm dacă există deja contul de admin, ca să nu-l creăm de 100 de ori
			if (utilizatorRepository.findByNumeUtilizator("admin").isEmpty()) {
				Utilizator admin = new Utilizator();
				admin.setNumeUtilizator("admin");
				admin.setParolaCriptata(passwordEncoder.encode("admin123")); // Criptăm parola!
				admin.setEmail("admin@facultate.ro");
				admin.setRol(Utilizator.Rol.Admin);
				
				utilizatorRepository.save(admin);
				System.out.println("CONT ADMIN CREAT: User: admin | Parola: admin123");
			}
		};
	}
}