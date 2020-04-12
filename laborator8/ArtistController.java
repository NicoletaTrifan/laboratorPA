import com.github.javafaker.Faker;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistController implements Dao<Artist> {
    private Artist artist;
    private Artist searchedArtist;
    private Connection connection = Database.getConnection();
    private List<Artist> artistList;

    public ArtistController(Artist artist) {
        this.artist = artist;
    }

    public ArtistController() {
    }

    @Override
    public Optional<Artist> get(long id) {
        return Optional.ofNullable(artistList.get((int) id));
    }

    @Override
    public List<Artist> getAll() {
        artistList = new ArrayList<>();
        String query = "SELECT * FROM ARTISTS";
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Artist addArtist = new Artist(rs.getString(2), rs.getString(3));
                artistList.add(addArtist);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return artistList;
    }

    public void createGeneric() {
        create(artist.getName(), artist.getCountry());
    }

    public void find(Artist artist) {
        try {
            if (findByName(artist.getName()) == null) {
                System.out.println("Artist does not exist in database");
            } else {
                System.out.println(searchedArtist.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(String name, String country) {
        String query = "INSERT INTO ARTISTS (NAME, COUNTRY) VALUES (?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, country);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Artist findByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artists WHERE NAME = (?)");
        preparedStatement.setString(1, name);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            searchedArtist = new Artist(result.getString(2), result.getString(3));
            return searchedArtist;
        }
        return null;
    }
}
