package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addCheck(x -> x instanceof String && !x.toString().isEmpty());
        return this;
    }

    public StringSchema contains(Object inputValue) {
        addCheck(x -> !(x instanceof String) || x.toString().isEmpty() || x.toString().contains(inputValue.toString()));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        addCheck(x -> !(x instanceof String) || x.toString().isEmpty() || x.toString().length() >= inputMinLength);
        return this;
    }
}
