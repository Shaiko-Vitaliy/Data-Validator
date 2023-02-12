package hexlet.code;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> predicateList;
    private Object stringIsValid;

    protected final void setPredicateList(List<Predicate<Object>> list) {
        this.predicateList = list;
    }

    protected final void addPredicateList(Predicate<Object> predicate) {
        this.predicateList.add(predicate);
    }

    protected final Object getStringIsValid() {
        return this.stringIsValid;
    }

    public final boolean isValid(Object inputIsValid) {
        this.stringIsValid = inputIsValid;
       //System.out.println(stringIsValid);
        return predicateList.stream().allMatch(x -> x.test(inputIsValid));
    }
}
