import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming implements Algorithm {

    private Knapsack knapsack;
    private List<Item> availableItems;
    private List<Item> selectedItems;
    private int capacity;
    private int profit = 0;
    private int[][] knapsackArray;

    public DynamicProgramming(Knapsack knapsack, int capacity) {
        this.knapsack = knapsack;
        this.capacity = capacity;
        selectedItems = new ArrayList<>();
        knapsackArray = new int[500][500];
    }

    public void setAvailableItems(List<Item> availableItems) {
        this.availableItems = availableItems;
    }

    @Override
    public void solver() {
        int length = availableItems.size();
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    knapsackArray[i][j] = 0;
                } else if (availableItems.get(i - 1).getWeight() <= j) {
                    int notAddedItem = knapsackArray[i - 1][j];
                    int addedItem = knapsackArray[i - 1][j - availableItems.get(i - 1).getWeight()] + availableItems.get(i - 1).getValue();
                    knapsackArray[i][j] = Math.max(notAddedItem, addedItem);
                } else {
                    knapsackArray[i][j] = knapsackArray[i - 1][j];
                }
            }
        }
        this.profit = knapsackArray[length][capacity];
        int aux = this.profit;
        int j = this.capacity;
        for (int i = length; i > 0 && aux > 0; i--) {
            if (aux != knapsackArray[i - 1][j]) {
                selectedItems.add(availableItems.get(i - 1));
                aux -= availableItems.get(i - 1).getValue();
                j -= availableItems.get(i - 1).getWeight();
            }
        }
        knapsack.setCapacity(capacity);
        knapsack.setItems(selectedItems);

    }

    public int getProfit() {
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
