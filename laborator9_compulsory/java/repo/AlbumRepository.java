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
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AlbumRepository implements AbstractRepository<Album> {

    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();


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
