package net.springboot.javaguides.helper;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToolHelperTest {

    @InjectMocks
    ToolHelper helper;

    @Test
    public void generateIdSucces() {
        String id = helper.generateId();

        // Vérification
        assertNotNull(id);
        assertTrue(Strings.isNotEmpty(id));
    }

    @Test
    public void generateIdDifferentId() {
        String id1 = helper.generateId();
        String id2 = helper.generateId();

        // Vérification
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
    }

    @Test
    public void fibonacciSucces() {
        int x = 10;
        int f = 55;

        int result = helper.fibonacci(x);

        // Vérification
        assertNotNull(result);
        assertEquals(result, f);
    }

    @Test
    public void fibonacciError() {
        int x = 9;
        int f10 = 55;

        int f9 = helper.fibonacci(x);

        // Vérification
        assertNotEquals(f10, f9);
    }
}