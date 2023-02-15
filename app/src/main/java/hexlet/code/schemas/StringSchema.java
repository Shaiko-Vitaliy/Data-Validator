package hexlet.code.schemas;

import java.util.ArrayList;

public final class StringSchema extends BaseSchema {
    private Object valueOnValid;
    private String valueContains;
    private int minLength;
    private boolean defaultSwitch = true;

    public StringSchema() {
        setPredicateList(new ArrayList<>());
        addPredicateList(x -> isValidDefault());
    }

    @Override
    public boolean isValid(Object input) {
        valueOnValid = input;
        return getPredicateList().stream().allMatch(x -> x.test(this.valueOnValid));
    }

    private boolean isValidDefault() {
        if (defaultSwitch) {
            return valueOnValid == null || valueOnValid.equals("")
                    || valueOnValid instanceof String;
        }
        return true;
    }

    public StringSchema required() {
        defaultSwitch = false;
        addPredicateList(x -> valueOnValid instanceof String
                && valueOnValid.toString().length() > 0);
        return this;
    }

    public StringSchema contains(Object inputValue) {
        this.valueContains = inputValue.toString();
        this.required();
        addPredicateList(x -> valueOnValid.toString().contains(valueContains));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        this.minLength = Math.max(inputMinLength, 0);
        this.required();
        addPredicateList(x -> valueOnValid.toString().length() >= this.minLength);
        return this;
    }
}
