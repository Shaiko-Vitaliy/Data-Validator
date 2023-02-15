package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Object valueOnValid;
    private boolean defaultSwitch = true;
    private boolean shapeSwitch = false;
    private Map<String, BaseSchema> schemasMap;
    private int sizeMap;

    public MapSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    @Override
    public boolean isValid(Object input) {
        this.valueOnValid = input;
        if (!shapeSwitch) {
            return getPredicateList().stream().allMatch(x -> x.test(this.valueOnValid));
        }
        for (Map.Entry<String, BaseSchema> base : this.schemasMap.entrySet()) {
            for (Map.Entry<?, ?> item : ((Map<?, ?>) valueOnValid).entrySet()) {
                if (base.getKey().equals(item.getKey())) {
                    BaseSchema schema = base.getValue();
                    if (!schema.isValid(item.getValue())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return valueOnValid == null || valueOnValid instanceof Map<?, ?>;
        }
        return true;
    }

    public MapSchema required() {
        shapeSwitch = false;
        defaultSwitch = false;
        addPredicateList(x -> valueOnValid instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeMap = size;
        if (!(valueOnValid instanceof Map<?, ?>)) {
            addPredicateList(x -> valueOnValid instanceof Map<?, ?>);
            return this;
        }
        addPredicateList(x -> ((Map<?, ?>) valueOnValid).size() == this.sizeMap);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.schemasMap = schemas;
        shapeSwitch = true;
    }
}
