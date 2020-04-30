package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import util.*;

import static util.PersistenceUtil.getInstance;

public class ArtistRepository implements AbstractRepository<Artist> {


    private EntityManagerFactory entityManagerFactory= PersistenceUtil.getInstance().returnEntity();
    private Connection connection = Database.getConnection();

    public ArtistRepository() {
    }


    @Override
    public Optional<Artist> get(long id) {
        List<Artist> artists=this.getAll();
        return Optional.ofNullable(artists.get((int) id));
    }

    @Override
    public List<Artist> getAll() {
        List<Artist> artistList;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        artistList=entityManager.createNamedQuery("getAllArtist",Artist.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return artistList;
    }

    @Override
    public void create(Artist element) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Artist findByID(int id) {
       EntityManager entityManager=entityManagerFactory.createEntityManager();
       entityManager.getTransaction().begin();
       Artist artistSearched= entityManager.find(Artist.class, id);
       entityManager.getTransaction().commit();
       entityManager.close();
       return artistSearched;
    }

    @Override
    public Artist findByName(String name) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Artist artistSearched=entityManager.createNamedQuery("findArtistByName",Artist.class).setParameter("name",name).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return artistSearched;
    }

    @Override
    public Optional<Artist> getJDBC(long id) {
        List<Artist> artistList=this.getAllJDBC();
        return Optional.ofNullable(artistList.get((int) id));
    }

    @Override
    public List<Artist> getAllJDBC() {
        List<Artist> artistList=new ArrayList<>();
        String query = "SELECT * FROM ARTISTS";
        List<Album> albumList = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Artist artistAdd = new Artist( rs.getString(2), rs.getString(3));
                artistList.add(artistAdd);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return artistList;
    }

    @Override
    public void createJDBC(Artist element) {
        String query = "INSERT INTO ARTISTS (NAME,COUNTRY) VALUES (?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, element.getName());
            statement.setString(2, element.getCountry());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Artist findByIDJDBC(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artists WHERE artist_id= (?)");
        preparedStatement.setInt(1, id);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            Artist searchedArtist = new Artist(result.getString(2), result.getString(3));
            return searchedArtist;
        }
        return null;
    }

    @Override
    public Artist findByNameJDBC(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artists WHERE name= (?)");
        preparedStatement.setString(1, name);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            Artist searchedArtist = new Artist(result.getString(2), result.getString(3));
            return searchedArtist;
        }
        return null;
    }


}
