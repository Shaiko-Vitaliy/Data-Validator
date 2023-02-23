package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> validatorChecks = new ArrayList<>();
    private Map<String, BaseSchema> schemasMap;
    private Object valueToCheck;
    private Class<?> schemaClass;
    private boolean isRequired = false;
    private int minRange;
    private int maxRange;
    private String valueContains;
    private int minLength;
    private int sizeMap;

    protected final void setRequired(boolean required) {
        isRequired = required;
    }

    protected final void setSchemasMap(Map<String, BaseSchema> schemas) {
        this.schemasMap = schemas;
    }

    protected final void setSchemaClass(Class<?> input) {
        this.schemaClass = input;
    }

    protected final void setMinLength(int min) {
        this.minLength = min;
    }

    protected final void setValueContains(String value) {
        this.valueContains = value;
    }

    protected final void setMinRange(int min) {
        this.minRange = min;
    }

    protected final void setMaxRange(int max) {
        this.maxRange = max;
    }

    protected final void setSizeMap(int size) {
        this.sizeMap = size;
    }

    protected final Object getValueOnValid() {
        return valueToCheck;
    }

    protected final int getMinLength() {
        return minLength;
    }

    protected final int getMinRange() {
        return minRange;
    }

    protected final int getMaxRange() {
        return maxRange;
    }

    protected final String getValueContains() {
        return valueContains;
    }

    protected final Map<String, BaseSchema> getSchemasMap() {
        return schemasMap;
    }

    protected final int getSizeMap() {
        return sizeMap;
    }

    protected final void addPredicateList(Predicate<Object> predicate) {
        this.validatorChecks.add(predicate);
    }

    public final boolean isValid(Object input) {
        valueToCheck = input;
        if (!isRequired) {
            if (valueToCheck == null || (schemaClass.equals(String.class) && valueToCheck.equals(""))) {
                return true;
            }
            if (!schemaClass.isInstance(valueToCheck)) {
                return false;
            }
        }
        if (isRequired) {
            if (!schemaClass.isInstance(valueToCheck) ||
                    schemaClass.equals(String.class) && valueToCheck.equals("")) {
                return false;
            }
        }
        return validatorChecks.stream().allMatch(x -> x.test(this.valueToCheck));
    }
}
