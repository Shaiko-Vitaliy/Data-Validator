package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        addCheck(x -> x instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(x -> !(x instanceof Map<?, ?>) || ((Map<?, ?>) x).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        for (Map.Entry<String, BaseSchema> base : schemas.entrySet()) {
            addCheck(x -> base.getValue().isValid((((Map<?, ?>) x)).get(base.getKey())));
        }
        return this;
    }
}
