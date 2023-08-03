package com.RathgarTogether;

import com.RathgarTogether.entities.User;
import com.RathgarTogether.enums.UserRole;
import com.RathgarTogether.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RathgarTogetherApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RathgarTogetherApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null == adminAccount){
			User user = new User();
			user.setEmail("aslipasoglu@hotmail.com");
			user.setName("Asli");
			user.setSurname("Pasoglu");
			user.setPhone("7696786786");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
