/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad1;

import java.util.LinkedList;
import java.util.List;

public class ListCreator<T> extends LinkedList<T> {

    public ListCreator(List<T> list) {
        super(list);
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Selector<T> sel) {
        List<T> selectedList = new LinkedList<>();
        for (T t : this) {
            if (sel.select(t))
                selectedList.add(t);
        }
        return new ListCreator<>(selectedList);
    }

    public <R> List<R> mapEvery(Mapper<T, R> mapper) {
        List<R> mappedList = new LinkedList<>();
        for (T t : this) {
            mappedList.add(mapper.map(t));
        }
        return mappedList;
    }
}