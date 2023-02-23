package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        setSchemaClass(String.class);
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public StringSchema contains(Object inputValue) {
        setValueContains(inputValue.toString());
        this.required();
        addPredicateList(x -> getValueOnValid().toString().contains(getValueContains()));
        return this;
    }

    public StringSchema minLength(int inputMinLength) {
        setMinLength(Math.max(inputMinLength, 0));
        this.required();
        addPredicateList(x -> getValueOnValid().toString().length() >= getMinLength());
        return this;
    }
}
