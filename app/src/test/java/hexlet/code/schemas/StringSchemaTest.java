package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
        assertFalse(stringSchema.isValid(new HashMap<>()));
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

        StringSchema stringSchema2 = validator.string();
        stringSchema2.contains("qwerty");
        assertFalse(stringSchema2.isValid(null));
        assertFalse(stringSchema2.isValid(num));
        assertFalse(stringSchema2.isValid(""));
        assertFalse(stringSchema2.isValid("string is sub true"));
        assertTrue(stringSchema2.isValid("string is sub true qwerty"));

        StringSchema stringSchema3 = validator.string();
        stringSchema3.minLength(minLength);
        assertFalse(stringSchema3.isValid(null));
        assertFalse(stringSchema3.isValid(num));
        assertFalse(stringSchema3.isValid(""));
        assertFalse(stringSchema3.isValid("qwerty"));
        assertTrue(stringSchema3.isValid("string is true qwerty"));
    }
}


