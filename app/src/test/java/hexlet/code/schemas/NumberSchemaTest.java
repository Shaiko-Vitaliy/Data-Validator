package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private final int numPositive = 22;
    private final int numNegative = -22;
    private final int minRange = -11;
    private final int maxRange = 22;

    @Test
    public void numberSchemaTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(new HashMap<>()));
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(numPositive));
        assertTrue(numberSchema.isValid(numNegative));

        numberSchema.required();
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(numPositive));
        assertTrue(numberSchema.isValid(numNegative));

        numberSchema.positive();
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(numNegative));
        assertTrue(numberSchema.isValid(numPositive));

        numberSchema.range(minRange, maxRange);
        assertFalse(numberSchema.isValid("string"));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(numNegative));
        assertTrue(numberSchema.isValid(numPositive));
        assertTrue(numberSchema.isValid(1));

        NumberSchema numberSchema2 = validator.number();
        numberSchema2.positive();
        assertTrue(numberSchema2.isValid(null));
        assertFalse(numberSchema2.isValid("string"));
        assertTrue(numberSchema2.isValid(numPositive));
        assertFalse(numberSchema2.isValid(numNegative));

        NumberSchema numberSchema3 = validator.number();
        numberSchema3.range(minRange, maxRange);
        assertFalse(numberSchema3.isValid(null));
        assertFalse(numberSchema3.isValid("string"));
        assertTrue(numberSchema3.isValid(numPositive));
    }
}
