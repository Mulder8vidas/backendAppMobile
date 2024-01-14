package com.example.login;

import com.example.login.entity.AuthEntity;
import com.example.login.entity.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

    @Autowired
    private AuthRepository authRepository;

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<AuthEntity> all = this.authRepository.findAll();
        all.forEach(authEntity -> {

            System.out.println(authEntity);
        });


    }
}
