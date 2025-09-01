package com.rubix.dti.ZooProject;

import com.rubix.dti.ZooProject.view.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZooProjectApplication implements CommandLineRunner {

    @Autowired
    CommandLine CommandLine;


    public ZooProjectApplication(CommandLine CommandLine) {
        this.CommandLine = CommandLine;

    }

    public static void main(String[] args) {

        SpringApplication.run(ZooProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CommandLine.init();
    }
}
