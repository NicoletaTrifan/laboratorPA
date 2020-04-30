package app;

import com.github.javafaker.Faker;
import entity.Artist;
import entity.Artist;
import entity.Album;
import entity.Chart;
import org.w3c.dom.ls.LSOutput;
import repo.*;

import util.*;

import java.sql.SQLException;

public class AlbumManager {
    public static void main(String[] args) throws SQLException {
        Faker faker = new Faker();
        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist(faker.name().fullName(), faker.country().name());
        Artist artist2 = new Artist();
        artist2.setName("Connie Kuvalis");
        artist2.setCountry("United States of America");
        artist2.setId(12);
        //artistRepository.create(artist);
        System.out.println(artistRepository.findByName("Marlo Stamm"));
        System.out.println(artistRepository.findByID(1));
        System.out.println();
        System.out.println(artistRepository.getAll().size());
        System.out.println();
        System.out.println(artistRepository.get(3));
        System.out.println();
        AlbumRepository albumRepository = new AlbumRepository();
        Album album = new Album();
        album.setName(faker.hipster().word());
        album.setArtistID(12);
        album.setReleaseYear((int) ((Math.random() * 30) + 1990));
        // albumRepository.create(album);
        System.out.println(albumRepository.findByName("tacos"));
        System.out.println(albumRepository.findByID(12));
        System.out.println();
        albumRepository.findByArtist(artist2).stream().forEach(System.out::println);
        albumRepository.getAll().stream().forEach(System.out::println);
        ChartRepository chartRepository = new ChartRepository();
        Chart chart = new Chart(1, 1, 5);
        chartRepository.showTop(6).stream().forEach(System.out::println);
        System.out.println();
        chartRepository.getAllJDBC().stream().forEach(System.out::println);
        // chartRepository.createJDBC(chart);
        artistRepository.getAllJDBC().stream().forEach(System.out::println);
        System.out.println(artistRepository.findByNameJDBC("Marlo Stamm"));
        for (int i=0; i<10 ; i++) {
            System.out.println(faker.music().genre());
        }
    }
}
