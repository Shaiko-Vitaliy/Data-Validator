package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class MapSchemaTest {
    private final int numPositive = 22;
    private final int sizeMap = 2;

    @Test
    public void mapSchemaTest() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(mapSchema.isValid("string"));
        assertFalse(mapSchema.isValid(numPositive));
        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        assertTrue(mapSchema.isValid(data));

        mapSchema.required();
        assertFalse(mapSchema.isValid("string"));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(numPositive));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        assertTrue(mapSchema.isValid(data));

        mapSchema.required();
        assertFalse(mapSchema.isValid("string"));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(numPositive));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        assertTrue(mapSchema.isValid(data));

        mapSchema.sizeof(sizeMap);
        assertFalse(mapSchema.isValid("string"));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(numPositive));
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }
}
