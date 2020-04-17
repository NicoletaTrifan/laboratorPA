package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "findArtistByName", query = "Select a from Artist a where a.name=:name"),
        @NamedQuery(name = "getAllArtist", query = "Select a from Artist a"),
})
public class Artist {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;

    public int getId() {
        return id;
    }

    public Artist() {
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "entity.Artist{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
