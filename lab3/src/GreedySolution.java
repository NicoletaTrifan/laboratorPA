import java.util.ArrayList;
import java.util.List;

public class GreedySolution implements Algorithm {
    private Knapsack knapsack;
    private List<Item> availableItems = new ArrayList<>();
    private List<Item> selectedItems = new ArrayList<>();
    private int capacity;
    private double profit;

    public GreedySolution(Knapsack knapsack, int capacity) {
        this.knapsack = knapsack;
        this.capacity = capacity;
    }

    public void setAvailableItems(List<Item> availableItems) {
        this.availableItems = availableItems;
    }

    public static int compareByProfit(Item i1, Item i2) {
        double profitItem1 = i1.profitFactor();
        double profitItem2 = i2.profitFactor();
        if (profitItem1 == profitItem2) {
            return Integer.compare(i2.getValue(), i1.getValue());
        }
        if (profitItem2 > profitItem1) {
            return 1;
        }
        return -1;
    }

    public void sortByComparator() {
        availableItems.sort(GreedySolution::compareByProfit);
    }

    @Override
    public void solver() {
        sortByComparator();
        int index = 0;
        while (index < availableItems.size() && (capacity - availableItems.get(index).getWeight() >= 0)) {
            capacity -= availableItems.get(index).getWeight();
            profit += availableItems.get(index).profitFactor();
            selectedItems.add(availableItems.get(index));
            index++;

        }
        knapsack.setItems(selectedItems);
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public void printSolution() {
        System.out.println("Profitul maxim este : " + getProfit());
        System.out.println("Elemntele selectate sunt : ");
        for (Item item : selectedItems) {
            System.out.println(item + " ");
        }

    }
}
