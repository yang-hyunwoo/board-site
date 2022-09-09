package com.board.boardsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BoardSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardSiteApplication.class, args);
    }

}
