package domain.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    @Test
    void sum() {
        Sum sum = new Sum();
        int res = sum.sum(2, 3);
        Assertions.assertEquals(5, res);
    }
}