package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MapSchemaTest {
    private final int numPositive = 22;
    private final int numNegative = -22;

    @Test
    public void mapValidatorTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("string")).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();
    }

    @Test
    public void shapeTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", numPositive);
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isTrue();

        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertThat(schema.isValid(actual4)).isFalse();

        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", numNegative);
        assertThat(schema.isValid(actual5)).isFalse();

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", numPositive);
        assertThat(schema.isValid(actual6)).isFalse();
    }
}
