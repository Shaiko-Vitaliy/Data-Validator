package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck("required", x -> x instanceof String && !x.toString().isEmpty());
    }
    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema contains(Object inputValue) {
        addCheck("contains", x ->
                !(x instanceof String) || x.toString().isEmpty() || x.toString().contains(inputValue.toString()));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        addCheck("minLength", x ->
                !(x instanceof String) || x.toString().isEmpty() || x.toString().length() >= inputMinLength);
        return this;
    }
}
