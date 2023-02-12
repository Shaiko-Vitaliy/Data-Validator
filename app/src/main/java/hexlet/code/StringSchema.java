package hexlet.code;

import java.util.ArrayList;

public final class StringSchema extends BaseSchema {
    private String value;
    private int minLength;
    private boolean defaultSwitch = true;

    public StringSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return getStringIsValid() == null || getStringIsValid().equals("")
                    || getStringIsValid() instanceof String;
        }
        return true;
    }

    public StringSchema required() {
        defaultSwitch = false;
        addPredicateList(x -> getStringIsValid() instanceof String
                && getStringIsValid().toString().length() > 0);
        return this;
    }

    public StringSchema contains(Object inputValue) {
        this.value = inputValue.toString();
        if (!(getStringIsValid() instanceof String)) {
            addPredicateList(x -> getStringIsValid() instanceof String);
            return this;
        }
        addPredicateList(x -> getStringIsValid().toString().contains(this.value));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        this.minLength = Math.max(inputMinLength, 0);
        if (!(getStringIsValid() instanceof String)) {
            addPredicateList(x -> getStringIsValid() instanceof String);
            return this;
        }
        addPredicateList(x -> getStringIsValid().toString().length() >= this.minLength);
        return this;
    }
}
