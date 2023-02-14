package hexlet.code.schemas;

import java.util.ArrayList;

public final class NumberSchema extends BaseSchema {
    private int minRange;
    private int maxRange;
    private boolean defaultSwitch = true;


    public NumberSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return getStringIsValid() == null || getStringIsValid() instanceof Integer;
        }
        return true;
    }
    public NumberSchema required() {
        defaultSwitch = false;
        addPredicateList(x -> getStringIsValid() instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        this.required();
        addPredicateList(x -> (Integer) getStringIsValid() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        minRange = min;
        maxRange = max;
        this.required();
        addPredicateList(x -> (Integer) getStringIsValid() >= minRange
                && (Integer) getStringIsValid() <= maxRange);
        return this;
    }
}
