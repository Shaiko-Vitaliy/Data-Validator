package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private boolean required = false;
    private final Map<String, Predicate<Object>> validatorChecks = new HashMap<>();

    protected final void addCheck(String key, Predicate<Object> predicate) {
        this.validatorChecks.put(key, predicate);
    }

    /**
     * @return Возвращаемое значение не используется.
     */
    protected BaseSchema required() {
        required = true;
        return this;
    }

    public final boolean isValid(Object input) {
        if (!required) {
            Predicate<Object> validate = validatorChecks.get("required");
            if (!validate.test(input)) {
                return true;
            }
        }
        for (Map.Entry<String, Predicate<Object>> item : validatorChecks.entrySet()) {
            if (!item.getValue().test(input)) {
                return false;
            }
        }
        return true;
    }
}
