package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class NumberSchemaText {
    private final int numPositive = 22;
    private final int numNegative = -22;
    private final int minRange = -11;
    private final int maxRange = 22;

    @Test
    public void requiredTest() {
        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();
        assertFalse(numberSchema.isValid("string"));
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(numPositive));
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
    }
}
