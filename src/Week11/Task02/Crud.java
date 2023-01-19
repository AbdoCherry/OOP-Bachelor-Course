package Week11.Task02;

import java.util.Set;

public interface Crud<T> {

    public abstract void create(Set<T> t);

    public abstract void remove(Set<T> t);
}
