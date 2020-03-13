import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int capacity;
    private List<Item> items = new ArrayList<>();

    public Knapsack() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public static int compareByName(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
    }

    public void sortByComparator() {
        items.sort(Knapsack::compareByName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sortByComparator();
        double computedValue = 0;
        sb.append("Selected items  : ");
        for (Item item : items) {
            sb.append(item);
            computedValue += item.getValue();
            sb.append("  \n");
        }
        sb.append("Knapsack total capacity : ");
        sb.append(getCapacity());
        sb.append("  total value : ");
        sb.append(computedValue);
        return sb.toString();

    }
}
