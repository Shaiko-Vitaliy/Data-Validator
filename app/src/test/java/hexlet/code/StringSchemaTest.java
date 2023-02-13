package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private final int minLength = 7;
    private final int num = 22;
    @Test
    public void stringSchemaTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();

        assertFalse(stringSchema.isValid(num));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("string"));

        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("string"));

        assertTrue(stringSchema.contains("sub2").isValid("string is sub2 true"));

        stringSchema.contains("qwerty");
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid("string is sub true"));
        assertTrue(stringSchema.isValid("string is sub true qwerty"));

        stringSchema.minLength(minLength);
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(num));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid("qwerty"));
        assertTrue(stringSchema.isValid("string is true qwerty"));
    }
}
