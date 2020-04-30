package entity;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jdk.jfr.Name;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "chart")
@NamedQueries({
        @NamedQuery(name = "top", query = "Select c from Chart c ORDER BY votes desc"),
        @NamedQuery(name = "getAllChart", query = "Select c from Chart c"),

})
public class Chart {
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

    @Column(name = "artist_id")
    private int artistID;
    @Column(name = "album_id")
    private int albumID;
    @Column(name = "votes")
    private int votes;

    public Chart() {
    }

    public Chart(int artistID, int albumID, int votes) {
        this.artistID = artistID;
        this.albumID = albumID;
        this.votes = votes;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Chart{" +
                ", artistID=" + artistID +
                ", albumID=" + albumID +
                ", votes=" + votes +
                '}';
    }
}
