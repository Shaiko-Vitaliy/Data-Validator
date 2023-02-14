package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> predicateList;
    private Object valueOnValid;
    private Map<String, BaseSchema> schemasMap;
    private boolean shapeSwitch = false;
    private String value;

    protected final void setPredicateList(List<Predicate<Object>> list) {
        this.predicateList = list;
    }
    protected final void setSchemasMap(Map<String, BaseSchema> map) {
        this.schemasMap = map;
    }
    protected final void setShapeSwitch(boolean shape) {
        this.shapeSwitch = shape;
    }
    protected final void setValue(String val) {
        this.value = val;
    }
    protected final void addPredicateList(Predicate<Object> predicate) {
        this.predicateList.add(predicate);
    }
    protected final Object getStringIsValid() {
        return this.valueOnValid;
    }
    protected final String getValue() {
        return this.value;
    }

    public final boolean isValid(Object inputOnValid) {
        this.valueOnValid = inputOnValid;
        if (!shapeSwitch) {
            return predicateList.stream().allMatch(x -> x.test(this.valueOnValid));
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
}
