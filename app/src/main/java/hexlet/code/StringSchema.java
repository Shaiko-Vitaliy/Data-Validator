package hexlet.code;

public final class StringSchema {
    private Object stringIsValid;
    private String action;
    private String value;
    private int minLength;

    public StringSchema(Object inputStringIsValid, String inputAction, String inputValue, int inputMinLength) {
        this.stringIsValid = inputStringIsValid;
        this.action = inputAction;
        this.value = inputValue;
        this.minLength = inputMinLength;
    }

    public boolean isValid(Object inputStringIsValid) {
        this.stringIsValid = inputStringIsValid;
        switch (action) {
            case "default" -> {
                return isValidDefault();
            }
            case "required" -> {
                return isValidRequired();
            }
            case "contains" -> {
                return isValidContains();
            }
            case "minLength" -> {
                return isValidMinLength();
            }
            default -> {
                return false;
            }
        }
    }

    private boolean isValidDefault() {
        return this.stringIsValid == null || this.stringIsValid.equals("") || this.stringIsValid instanceof String;
    }

    private boolean isValidRequired() {
        return this.stringIsValid instanceof String && this.stringIsValid.toString().length() > 0;
    }

    private boolean isValidContains() {
        if (!(this.stringIsValid instanceof String)) {
            return false;
        }
        return this.stringIsValid.toString().contains(this.value);
    }

    private boolean isValidMinLength() {
        if (!(this.stringIsValid instanceof String)) {
            return false;
        }
        return stringIsValid.toString().length() >= this.minLength;
    }

    public StringSchema required() {
        action = "required";
        return new StringSchema(this.stringIsValid, this.action, this.value, this.minLength);
    }

    public StringSchema contains(Object inputValue) {
        action = "contains";
        this.value = inputValue.toString();
        return new StringSchema(this.stringIsValid, this.action, this.value, this.minLength);
    }

    public StringSchema minLength(int inputMinLength) {
        this.action = "minLength";
        this.minLength = Math.max(inputMinLength, 0);
        return new StringSchema(this.stringIsValid, this.action, this.value, this.minLength);
    }
}
