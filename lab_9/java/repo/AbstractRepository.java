package repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T> {
    Optional<T> get(long id);

    List<T> getAll();

    void create(T element);

    T findByID(int id);

    T findByName(String name);

    Optional<T> getJDBC(long id);

    List<T> getAllJDBC();   //findAll

    void createJDBC(T element);

    T findByIDJDBC(int id) throws SQLException;

    T findByNameJDBC(String name) throws SQLException;

}
