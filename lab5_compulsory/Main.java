import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Main {
    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException, InvalidPathException {
        Catalog catalog = new Catalog("Java Resources", "D:\\an2_sem2\\java_tutorial(PA)\\lab5_compulsory\\catalog.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws InvalidCatalogException, IOException, ClassNotFoundException {
        Catalog catalog = CatalogUtil.load("D:\\an2_sem2\\java_tutorial(PA)\\lab5_compulsory\\catalog.ser");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}
