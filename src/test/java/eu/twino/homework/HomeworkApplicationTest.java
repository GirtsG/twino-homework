package eu.twino.homework;

import eu.twino.homework.controller.RootController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HomeworkApplicationTest {

    @Autowired
    private RootController rootController;

    @Test
    void contextLoads() {
        assertThat(rootController).isNotNull();
    }
}