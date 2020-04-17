package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import util.*;

import static util.PersistenceUtil.getInstance;

public class ArtistRepository implements AbstractRepository<Artist> {


    private EntityManagerFactory entityManagerFactory= PersistenceUtil.getInstance().returnEntity();

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
}
