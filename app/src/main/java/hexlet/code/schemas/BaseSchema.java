package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> validatorChecks = new ArrayList<>();

    protected final void addCheck(Predicate<Object> predicate) {
        this.validatorChecks.add(predicate);
    }

    public final boolean isValid(Object input) {
        return validatorChecks.stream().allMatch(x -> x.test(input));
    }
}
