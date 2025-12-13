package com.victor.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victor.workshopmongo.domain.Post;
import com.victor.workshopmongo.domain.User;
import com.victor.workshopmongo.repository.PostRepository;
import com.victor.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		//limpa as coleções no MongoDB
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para o Rio. Abraços!", maria );
		Post post2 = new Post(null, sdf.parse("21/05/2022"), "Niver date", "Hoje é meu aniversário!", bob);
		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
