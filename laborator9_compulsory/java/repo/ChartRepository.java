package repo;

import entity.Artist;
import entity.Chart;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ChartRepository implements AbstractRepository<Chart> {
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();

    @Override
    public Optional<Chart> get(long id) {
        List<Chart> charts=this.getAll();
        return Optional.ofNullable(charts.get((int) id));
    }

    @Override
    public List<Chart> getAll() {
        List<Chart> chartList;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        chartList=entityManager.createNamedQuery("getAllChart",Chart.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return chartList;
    }

    @Override
    public void create(Chart element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Chart findByID(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Chart chartSearched = entityManager.find(Chart.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return chartSearched;
    }

    @Override
    public Chart findByName(String name) {
        return null;
    }

    public List<Chart> showTop(int limit) {
        List<Chart> chartList;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        chartList = entityManager.createNamedQuery("top", Chart.class).setMaxResults(limit).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return chartList;
    }
}
