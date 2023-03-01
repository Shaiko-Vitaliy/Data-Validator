package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addCheck(x -> x instanceof Number);
        return this;
    }

    public NumberSchema positive() {
        addCheck(x -> !(x instanceof Integer) || (Integer) x > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck((x -> !(x instanceof Integer) || (Integer) x >= min
                && (Integer) x <= max));
        return this;
    }
}
