package hexlet.code.schemas;

import java.util.ArrayList;

public final class StringSchema extends BaseSchema {
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
        setValue(inputValue.toString());
        this.required();
        addPredicateList(x -> getStringIsValid().toString().contains(getValue()));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        this.minLength = Math.max(inputMinLength, 0);
        this.required();
        addPredicateList(x -> getStringIsValid().toString().length() >= this.minLength);
        return this;
    }
}
