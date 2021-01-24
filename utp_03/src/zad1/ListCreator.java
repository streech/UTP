package zad1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> extends LinkedList<T> {

    public ListCreator(List<T> list) {
        super(list);
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Predicate<T> predicate) {
        List<T> selectedList = new LinkedList<>();
        for (T t : this) {
            if (predicate.test(t))
                selectedList.add(t);
        }
        return new ListCreator<>(selectedList);
    }

    public <R> List<R> mapEvery(Function<T, R> function) {
        List<R> mappedList = new LinkedList<>();
        for (T t : this) {
            mappedList.add(function.apply(t));
        }
        return mappedList;
    }
}
