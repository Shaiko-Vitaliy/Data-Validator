package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        setSchemaClass(Map.class);
    }

    public MapSchema required() {
        setRequired(true);
        return this;
    }

    public MapSchema sizeof(int size) {
        setSizeMap(size);
        addPredicateList(x -> ((Map<?, ?>) getValueOnValid()).size() == getSizeMap());
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        setSchemasMap(schemas);
        addPredicateList(x -> {
            for (Map.Entry<String, BaseSchema> base : getSchemasMap().entrySet()) {
                for (Map.Entry<?, ?> item : ((Map<?, ?>) getValueOnValid()).entrySet()) {
                    if (base.getKey().equals(item.getKey())) {
                        BaseSchema schema = base.getValue();
                        if (!schema.isValid(item.getValue())) {
                            return false;
                        }
                    }
                }
            }
            return true;
        });
    }
}
