package ru.netology.transfer_card_spring;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferCardSpringApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> transferApp = new GenericContainer<>("myapp:1.0")
            .withExposedPorts(5500);


    @Test
    void contextLoads() {
        Integer appPort = transferApp.getMappedPort(5500);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + appPort, String.class);
        Assertions.assertNotNull(forEntity.getBody());
    }

}


