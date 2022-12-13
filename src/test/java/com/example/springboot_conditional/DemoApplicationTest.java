package com.example.springboot_conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> dev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prod = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        dev.start();
        prod.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forDev = restTemplate.getForEntity("http://localhost:" + dev.getMappedPort(8080) + "/profile", String.class);
        System.out.println("From dev: " + forDev.getBody());
        ResponseEntity<String> forProd = restTemplate.getForEntity("http://localhost:" + prod.getMappedPort(8081) + "/profile", String.class);
        System.out.println("From prod:" + forProd.getBody());
        Assertions.assertEquals(dev.getExposedPorts(), List.of(8080));
        Assertions.assertEquals(prod.getExposedPorts(), List.of(8081));
        Assertions.assertEquals(forDev.getBody(), "This is development profile");
        Assertions.assertEquals(forProd.getBody(), "This is production profile");
    }

}


