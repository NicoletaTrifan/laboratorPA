package repo;

import entity.MusicGenres;
import util.Database;
import util.PersistenceUtil;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GenresRepository implements AbstractRepository<MusicGenres>{
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();
    private Connection connection = Database.getConnection();


    @Override
    public Optional<MusicGenres> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<MusicGenres> getAll() {
        return null;
    }

    @Override
    public void create(MusicGenres element) {

    }

    @Override
    public MusicGenres findByID(int id) {
        return null;
    }

    @Override
    public MusicGenres findByName(String name) {
        return null;
    }

    @Override
    public Optional<MusicGenres> getJDBC(long id) {
        return Optional.empty();
    }

    @Override
    public List<MusicGenres> getAllJDBC() {
        return null;
    }

    @Override
    public void createJDBC(MusicGenres element) {

    }

    @Override
    public MusicGenres findByIDJDBC(int id) throws SQLException {
        return null;
    }

    @Override
    public MusicGenres findByNameJDBC(String name) throws SQLException {
        return null;
    }
}
