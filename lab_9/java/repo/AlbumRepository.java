package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Album;
import entity.Artist;
import util.Database;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class AlbumRepository implements AbstractRepository<Album> {

    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();
    private Connection connection = Database.getConnection();


    public AlbumRepository() {
    }

    @Override
    public Optional<Album> get(long id) {
        List<Album> albums = this.getAll();
        return Optional.ofNullable(albums.get((int) id));
    }

    @Override
    public List<Album> getAll() {
        List<Album> albumList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        albumList = entityManager.createNamedQuery("getAll", Album.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return albumList;
    }

    @Override
    public void create(Album element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Album findByID(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Album albumSearched = entityManager.find(Album.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return albumSearched;
    }

    @Override
    public Album findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Album albumSearched = entityManager.createNamedQuery("findAlbumByName", Album.class).setParameter("name", name).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return albumSearched;
    }

    @Override
    public Optional<Album> getJDBC(long id) {
        List<Album> albumList=this.getAllJDBC();
        return Optional.ofNullable(albumList.get((int) id));
    }

    @Override
    public List<Album> getAllJDBC() {
        String query = "SELECT * FROM ALBUMS";
        List<Album> albumList = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Album albumAdd = new Album(rs.getString(2), rs.getInt(3), rs.getInt(4));
                albumList.add(albumAdd);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return albumList;
    }

    @Override
    public void createJDBC(Album element) {
        String query = "INSERT INTO ALBUMS (NAME,ARTIST_ID,RELEASE_YEAR ) VALUES (?, ?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setInt(2, element.getArtistID());
            statement.setInt(3, element.getReleaseYear());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Album findByIDJDBC(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM albums WHERE artist_id= (?)");
        preparedStatement.setInt(1, id);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            Album searchedAlbum = new Album(result.getString(2), result.getInt(3), result.getInt(4));
            return searchedAlbum;
        }
        return null;
    }


    @Override
    public Album findByNameJDBC(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM albums WHERE name= (?)");
        preparedStatement.setString(1, name);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            Album searchedAlbum = new Album(result.getString(2), result.getInt(3), result.getInt(4));
            return searchedAlbum;
        }
        return null;
    }


    public List<Album> findByArtist(Artist artist) {
        List<Album> albumList;
        int artistID = artist.getId();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        albumList = entityManager.createNamedQuery("findByArtist", Album.class).setParameter("artistID", artistID).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return albumList;
    }
}
