package repo;

import entity.Artist;
import entity.Chart;
import util.Database;
import util.PersistenceUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChartRepository implements AbstractRepository<Chart> {
    private EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().returnEntity();
    private Connection connection = Database.getConnection();

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

    @Override
    public Optional<Chart> getJDBC(long id) {
        List<Chart> chartList=this.getAllJDBC();
        return Optional.ofNullable(chartList.get((int) id));
    }

    @Override
    public List<Chart> getAllJDBC() {
        String query = "SELECT * FROM Chart";
        List<Chart> chartList = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chart chartAdd = new Chart(rs.getInt(2), rs.getInt(3), rs.getInt(4));
                chartList.add(chartAdd);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return chartList;
    }

    @Override
    public void createJDBC(Chart element) {
        String query = "INSERT INTO chart (ARTIST_ID,ALBUM_ID, VOTES) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, element.getArtistID());
            statement.setInt(2, element.getAlbumID());
            statement.setInt(3, element.getVotes());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chart findByIDJDBC(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chart WHERE artist_id= (?)");
        preparedStatement.setInt(1, id);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            Chart searchedChart = new Chart(result.getInt(2), result.getInt(3), result.getInt(4));
            return searchedChart;
        }
        return null;
    }

    @Override
    public Chart findByNameJDBC(String name) {
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
