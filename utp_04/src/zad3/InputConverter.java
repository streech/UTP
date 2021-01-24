package zad3;

import java.util.function.Function;

public class InputConverter<T> {

    private T t;

    public InputConverter(T fname) {
        this.t = fname;
    }

    public <R> R convertBy(Function ... func) {
        Object input = t;
        Object result = null;

        for (Function f : func) {
            result = f.apply(input);
            input = result;
        }

        return (R) result;
    }
}
