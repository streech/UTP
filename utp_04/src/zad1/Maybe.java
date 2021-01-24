package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T t;

    public Maybe() {

    }

    public Maybe(T t) {
        this.t = t;
    }

    public static <T> Maybe<T> of(T t) {
        return new Maybe<>(t);
    }

    public void ifPresent(Consumer<T> cons) {
        if (isPresent()) cons.accept(t);
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        return (isPresent()) ? new Maybe<>(func.apply(t)) : new Maybe<>();
    }

    public T get() {
        if (isPresent()) return t;
        else throw new NoSuchElementException("maybe is empty");
    }

    public boolean isPresent() {
        return t != null;
    }

    public T orElse(T defVal) {
        return (isPresent()) ? t : defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        return !isPresent() ? new Maybe<>() : pred.test(t) ? this : new Maybe<>();
    }

    @Override
    public String toString() {
        return (isPresent()) ? "Maybe has value " + t : "Maybe is empty";
    }
}