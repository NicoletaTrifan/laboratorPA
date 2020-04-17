package repo;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T> {
    Optional<T> get(long id);

    List<T> getAll();

    void create(T element);

    T findByID(int id);

    T findByName(String name);
}
