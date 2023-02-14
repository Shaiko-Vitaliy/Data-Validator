package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private boolean defaultSwitch = true;
    private int sizeMap;

    public MapSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return getStringIsValid() == null || getStringIsValid() instanceof Map<?, ?>;
        }
        return true;
    }

    public MapSchema required() {
        setShapeSwitch(false);
        defaultSwitch = false;
        addPredicateList(x -> getStringIsValid() instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeMap = size;
        if (!(getStringIsValid() instanceof Map<?, ?>)) {
            addPredicateList(x -> getStringIsValid() instanceof Map<?, ?>);
            return this;
        }
        addPredicateList(x -> ((Map<?, ?>) getStringIsValid()).size() == this.sizeMap);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        setSchemasMap(schemas);
        setShapeSwitch(true);
    }


}
