import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) {
        Object obj = new Object();
        try (var ois = new ObjectInputStream(
                new FileInputStream(path))) {
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (Catalog) obj;
    }

    public static void view(Document doc) {
        try {
            if (!Desktop.isDesktopSupported()) {
                System.out.println("Desktop is not supported on the current platform !");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(doc.getLocation());
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
