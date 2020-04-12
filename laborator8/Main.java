import com.github.javafaker.Faker;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Faker faker = new Faker();
//        for (int i=0; i<2; i++) {
//            Artist artist = new Artist(faker.name().fullName(), faker.country().name());
//            ArtistController artistController = new ArtistController(artist);
//            artistController.createGeneric();
//        }
        Artist artistFound = new Artist("Beau Kuvalis", "Hungary");
        Artist artistNotFound = new Artist("Mary Gray", "America");
        ArtistController artistControllerSearch = new ArtistController();
        artistControllerSearch.find(artistFound);
        artistControllerSearch.find(artistNotFound);
//        for (int i=11; i<13 ; i++) {
//            Album album = new Album(faker.hipster().word(), i+1, (int)((Math.random()*30)+1990));
//            AlbumController albumController = new AlbumController(album);
//            albumController.createGeneric();
//        }
        System.out.println();
        System.out.println(artistControllerSearch.getAll());
        System.out.println(artistControllerSearch.get(2));
        System.out.println();
        Album albumFound = new Album(1);
        Album albumNotFound = new Album(20);
        AlbumController albumControllerSearch = new AlbumController();
        albumControllerSearch.find(albumFound);
        albumControllerSearch.find(albumNotFound);
//        for (int i=11;i<13 ; i++) {
//            int randomVotes=(int)((Math.random()*100)+1);
//            Chart chart = new Chart(i+1, i+1, randomVotes);
//            chart.addRow(chart);
//        }
        System.out.println();
        System.out.println(albumControllerSearch.getAll());
        System.out.println(albumControllerSearch.get(5));
        System.out.println();
        Chart topChart = new Chart();
        System.out.println("Top 5 albums in our chart :");
        topChart.showTop(5);
        topChart.showHTMLReport(15);
        Database.closeConnection();
    }
}
