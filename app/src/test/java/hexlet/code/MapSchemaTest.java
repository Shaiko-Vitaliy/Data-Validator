package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private final int numPositive = 22;
    private final int numNegative = -22;
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
    @Test
    public void shapeTest() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", numPositive);
        assertTrue(mapSchema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertFalse(mapSchema.isValid(human2)); // false

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3)); // false



        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", numNegative);
        assertFalse(mapSchema.isValid(human4)); // false


    }
}
