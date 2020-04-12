import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumController implements Dao<Album> {
    private Album album;
    private Album searchedAlbum;
    private Connection connection = Database.getConnection();
    private List<Album> albumList;

    public AlbumController(Album albumCreated) {
        album = albumCreated;
    }

    public AlbumController() {
    }

    @Override
    public Optional<Album> get(long id) {
        return Optional.ofNullable(albumList.get((int) id));
    }

    @Override
    public List<Album> getAll() {
        String query = "SELECT * FROM ALBUMS";
        albumList = new ArrayList<>();
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
    public void createGeneric() {
        create(album.getName(), album.getArtistID(), album.getReleaseYear());
    }

    public void create(String name, int id, int releaseYear) {
        String query = "INSERT INTO ALBUMS (NAME,ARTIST_ID,RELEASE_YEAR ) VALUES (?, ?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.setInt(3, releaseYear);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void find(Album albumToFind) {
        try {
            if (findByID(albumToFind.getArtistID()) == null) {
                System.out.println("Album does not exist in database");
            } else {

                System.out.println(searchedAlbum.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Album findByID(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM albums WHERE artist_id= (?)");
        preparedStatement.setInt(1, id);
        ResultSet result;
        result = preparedStatement.executeQuery();
        if (result.next()) {
            searchedAlbum = new Album(result.getString(2), result.getInt(3), result.getInt(4));
            return searchedAlbum;
        }
        return null;
    }
}
