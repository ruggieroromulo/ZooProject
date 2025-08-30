package com.rubix.dti.ZooProject;

import com.rubix.dti.ZooProject.utils.CommandLineInteraction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZooProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZooProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CommandLineInteraction.init();
    }
}
