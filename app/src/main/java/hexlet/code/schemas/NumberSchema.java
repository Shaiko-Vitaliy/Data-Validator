package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        setSchemaClass(Integer.class);
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        addPredicateList(x -> (Integer) getValueOnValid() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        setMinRange(min);
        setMaxRange(max);
        this.required();
        addPredicateList(x -> (Integer) getValueOnValid() >= getMinRange()
                && (Integer) getValueOnValid() <= getMaxRange());
        return this;
    }
}
