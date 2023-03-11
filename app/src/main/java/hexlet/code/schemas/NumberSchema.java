package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addCheck("required", x -> x instanceof Number);
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", x -> !(x instanceof Integer) || (Integer) x > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", (x -> !(x instanceof Integer) || (Integer) x >= min
                && (Integer) x <= max));
        return this;
    }
}
