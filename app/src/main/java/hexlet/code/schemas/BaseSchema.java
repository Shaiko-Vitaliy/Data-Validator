package hexlet.code.schemas;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> predicateList;

    protected final void setPredicateList(List<Predicate<Object>> list) {
        this.predicateList = list;
    }
    protected final void addPredicateList(Predicate<Object> predicate) {
        this.predicateList.add(predicate);
    }

    protected final List<Predicate<Object>> getPredicateList() {
        return predicateList;
    }

    public abstract boolean isValid(Object inputOnValid);

}
