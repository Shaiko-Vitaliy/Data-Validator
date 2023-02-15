package hexlet.code.schemas;

import java.util.ArrayList;

public final class NumberSchema extends BaseSchema {
    private Object valueOnValid;
    private int minRange;
    private int maxRange;
    private boolean defaultSwitch = true;


    public NumberSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    @Override
    public boolean isValid(Object input) {
        valueOnValid = input;
        if (defaultSwitch && valueOnValid == null) {
            return true;
        }
        if ((!defaultSwitch && valueOnValid == null) || !(valueOnValid instanceof Integer)) {
            return false;
        }
        return getPredicateList().stream().allMatch(x -> x.test(this.valueOnValid));
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return valueOnValid == null || valueOnValid instanceof Integer;
        }
        return true;
    }
    public NumberSchema required() {
        defaultSwitch = false;
        addPredicateList(x -> valueOnValid instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addPredicateList(x -> (Integer) valueOnValid > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        minRange = min;
        maxRange = max;
        this.required();
        addPredicateList(x -> (Integer) valueOnValid >= minRange
                && (Integer) valueOnValid <= maxRange);
        return this;
    }
}
