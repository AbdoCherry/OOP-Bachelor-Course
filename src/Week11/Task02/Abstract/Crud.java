package Week11.Task02.Abstract;

import java.util.Set;

public interface Crud<T> {

    void create(Set<T> t);

    void remove(Set<T> t);
}
