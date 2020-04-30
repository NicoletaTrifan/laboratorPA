package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "findAlbumByName", query = "Select a from Album a where a.name=:name"),
        @NamedQuery(name = "findByArtist", query = " Select a from Album a where a.artistID=:artistID"),
        @NamedQuery(name = "getAll", query = "Select a from Album a"),
})

public class Album {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "artist_id")
    private int artistID;
    @Column(name = "release_year")
    private int releaseYear;

    public Album(String name, int artistID, int releaseYear) {
        this.name = name;
        this.artistID = artistID;
        this.releaseYear = releaseYear;
    }

    public Album() {
    }

    public Album(int artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artistID=" + artistID +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
