package com.example.BackendApi;

import com.example.BackendApi.entity.Role;
import com.example.BackendApi.entity.User;
import com.example.BackendApi.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.BackendApi"})
@EnableJpaRepositories("com.example.BackendApi.repository")
public class BackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}


}
