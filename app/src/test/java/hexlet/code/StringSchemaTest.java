package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private final int minLength = 3;
    private final int num = 22;
    @Test
    public void requiredTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("string"));
        assertFalse(stringSchema.isValid(num));
        stringSchema.required();
        assertTrue(stringSchema.isValid("string"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertTrue(stringSchema.contains("sub2").isValid("string is sub2 true"));
        stringSchema.contains("qwerty");
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid("string is sub true"));
        stringSchema.minLength(minLength);
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid("st"));
        assertTrue(stringSchema.isValid("string is true"));
    }
}