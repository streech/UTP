package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XList<T> extends ArrayList<T> {

    public XList() {
        super();
    }

    public XList(T ... t) {
        super(Arrays.asList(t));
    }

    public XList(Collection<T> c) {
        super(c);
    }

    public static <T> XList<T> of(T ... t) {
        return new XList<>(t);
    }

    public static <T> XList<T> of(Collection<T> c) {
        return new XList<>(c);
    }

    public static XList<String> charsOf(String s) {
        return new XList<>(s.chars()
                .mapToObj(Character::toChars)
                .map(String::valueOf)
                .collect(Collectors.toList())
        );
    }

    public static XList<String> tokensOf(String s) {
        return new XList<>(s.split(" "));
    }

    public static XList<String> tokensOf(String s, String sep) {
        return new XList<>(s.split(sep));
    }

    public XList<T> union(T ... t) {
        return new XList<>(Stream
                .concat(stream(), Arrays.stream(t))
                .collect(Collectors.toList())
        );
    }

    public XList<T> union(Collection<T> c) {
        return new XList<>(Stream
                .concat(stream(), c.stream())
                .collect(Collectors.toList())
        );
    }

    public XList<T> diff(Collection<T> c) {
        XList<T> resultList = new XList<>();
            for (int j = 0; j < size(); j++) {
                if (!c.contains(get(j))) {
                    resultList.add(get(j));
                }
        }
        return resultList;
    }

    public XList<T> unique() {
        return new XList<>(stream().distinct().collect(Collectors.toList()));
    }

    public XList<XList<T>> combine() {
        return combine(this.size() - 1, (XList<XList<T>>) this);
    }

    private static <T> XList<XList<T>> combine(int i, XList<XList<T>> outerList) {
        XList<XList<T>> resultList = new XList<>();

        if (i < 0) {
            resultList.add(new XList<>());
        } else {
            Collection<T> col = outerList.get(i);
            for (T t : col) {
                for (XList<T> innerList : combine(i - 1, outerList)) {
                    innerList.add(t);
                    resultList.add(innerList);
                }
            }
        }

        return resultList;
    }

    public <R> XList<R> collect(Function<T, R> function) {
        XList<R> resultList = new XList<>();
        for (T t : this) {
            R apply = function.apply(t);
            resultList.add(apply);
        }
        return resultList;
    }

    public String join() {
        return join("");
    }

    public String join(String sep) {
        return stream().map(Object::toString).collect(Collectors.joining(sep));
    }

    public void forEachWithIndex(BiConsumer<T, Integer> biConsumer) {
        for (int i = 0; i < this.size(); i++) {
            biConsumer.accept(this.get(i), i);
        }
    }
}
