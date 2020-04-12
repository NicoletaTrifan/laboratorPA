import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

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

public class Chart {
    private int artistID;
    private int albumID;
    private int votes;
    private Connection connection = Database.getConnection();

    public Chart(int artistID, int albumID, int votes) {
        this.artistID = artistID;
        this.albumID = albumID;
        this.votes = votes;
    }

    public Chart() {
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

    public void addRow(Chart chart) {
        String query = "INSERT INTO chart (ARTIST_ID,ALBUM_ID, VOTES) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, chart.artistID);
            statement.setInt(2, chart.albumID);
            statement.setInt(3, chart.votes);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTop(int requestedNumber) {
        String queryCount = "SELECT COUNT(*) FROM CHART";
        String querySelect = "SELECT * FROM CHART ORDER BY VOTES DESC LIMIT ?";
        String querySelectAll = "SELECT * FROM CHART ORDER BY VOTES DESC";
        try {
            PreparedStatement statement = connection.prepareStatement(queryCount);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > requestedNumber) {
                    statement = connection.prepareStatement(querySelect);
                    statement.setInt(1, requestedNumber);
                    ResultSet rsSelect = statement.executeQuery();
                    while (rsSelect.next()) {
                        System.out.println("Artist with id: " + rsSelect.getInt(2) + " has Album with id : " + rsSelect.getInt(3) + " and " + rsSelect.getInt(4) + " votes");
                    }
                } else {
                    statement = connection.prepareStatement(querySelectAll);
                    ResultSet rsAll = statement.executeQuery();
                    while (rsAll.next()) {
                        System.out.println("Artist with id: " + rsAll.getInt(2) + " has Album with id : " + rsAll.getInt(3) + " and " + rsAll.getInt(4) + " votes");
                    }
                }
            } else {
                System.out.println("no rows");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showHTMLReport(int limit) throws IOException, SQLException {
        StringBuilder htmlfile = new StringBuilder();
        BufferedWriter file;
        PreparedStatement preparedStatement;

        String queryCount = "SELECT COUNT(*) FROM CHART";
        String querySelect = "SELECT a.name,c.artist_id,c.album_id,c.votes FROM CHART c join ARTISTS a on a.id=c.artist_id ORDER BY VOTES DESC LIMIT ?";
        String querySelectAll = "SELECT a.name,c.artist_id,c.album_id,c.votes FROM CHART c join ARTISTS a on a.id=c.artist_id ORDER BY VOTES DESC";

        htmlfile.append("<!DOCTYPE html>\n" + "<html lang=\"en\">\n");
        htmlfile.append("<head>\n" + "<meta charset=\"UTF-8\">\n" +
                " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Chart</title>\n" + "<style>\n" +
                "  h2 {\n" + " text-align: center;\n" + "color: #444;\n" + "margin: 15px;}\n" +
                " table {\n" + "width: 80%;\n" + "border: 1px solid gray;\n" + "text-align:center;" + "margin: 3% 10%;}\n" +
                "th {\n" + "color: white;\n" + "background-color:#009999;}\n" + "  th,td {\n" + "border: 1px solid gray;\n"
                + "padding: 1%;}\n" + " tr:hover {\n" +
                " transform: scale(1.03);\n" + "transition: .5s ease;}\n" + "tr:nth-child(even) {\n" +
                "background-color: #f2f2f2;}\n" + "</style>\n" + "</head>\n");
        htmlfile.append("<body>\n" + "<h2>Top " + limit + " artists</h2>\n" +
                "<table>\n" +
                "<tr>\n" + "<th>ARTIST NAME</th>\n" + "<th>ARTIST ID</th>\n" + "<th>ALBUM ID</th>\n" + "<th>VOTES</th>\n" + "</tr>\n");

        preparedStatement = connection.prepareStatement(queryCount);
        ResultSet rsCount = preparedStatement.executeQuery();
        while (rsCount.next()) {
            if (rsCount.getInt(1) > limit) {
                preparedStatement = connection.prepareStatement(querySelect);
                preparedStatement.setInt(1, limit);
                ResultSet rsSelect = preparedStatement.executeQuery();
                while (rsSelect.next()) {
                    htmlfile.append("<tr>" + "<td>" + rsSelect.getString(1) + "</td>\n" + "<td>" + rsSelect.getInt(2) + "</td>\n" + "<td>" + rsSelect.getInt(3) + "</td>\n" + "<td>" + rsSelect.getInt(4) + "</td>\n" + "</tr>\n");
                }
            } else {
                preparedStatement = connection.prepareStatement(querySelectAll);
                ResultSet rsAll = preparedStatement.executeQuery();
                while (rsAll.next()) {
                    htmlfile.append("<tr>" + "<td>" + rsAll.getString(1) + "</td>\n" + "<td>" + rsAll.getInt(2) + "</td>\n" + "<td>" + rsAll.getInt(3) + "</td>\n" + "<td>" + rsAll.getInt(4) + "</td>\n" + "</tr>\n");
                }
            }
        }

        htmlfile.append("</table>\n" + "</body>\n" + "</html>\n");
        file = new BufferedWriter(new FileWriter("report.html"));
        file.write(htmlfile.toString());
        file.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("report.html"));
    }
}
