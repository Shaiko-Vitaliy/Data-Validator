package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public  MapSchema() {
        addCheck("required", x -> x instanceof Map<?, ?>);
    }
    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", x -> !(x instanceof Map<?, ?>) || ((Map<?, ?>) x).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        for (Map.Entry<String, BaseSchema> base : schemas.entrySet()) {
            addCheck(base.toString(), x -> base.getValue().isValid((((Map<?, ?>) x)).get(base.getKey())));
        }
        return this;
    }
}
