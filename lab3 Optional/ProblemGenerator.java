import java.util.ArrayList;
import java.util.List;

public class ProblemGenerator {
    List<Knapsack> listOfKnapsacks = new ArrayList<>();
    private int numberOfProblems;

    public ProblemGenerator(int numberOfProblems) {
        this.numberOfProblems = numberOfProblems;
    }

    public Item itemGenerator(int randomItem, int randomValue, int randomWeight) {
        if (randomItem == 1) {
            return new Food("food", randomWeight);
        }
        if (randomItem == 2) {
            return new Book("book", randomWeight, randomValue);
        } else {
            return new Weapon(Weapon.WeaponType.SWORD, randomWeight, randomValue);
        }
    }

    public ArrayList<Item> generateItems() {
        ArrayList<Item> generatedList = new ArrayList<>();
        numberOfProblems = (int) (Math.random() * 25) + 1;
        for (int i = 0; i < numberOfProblems; i++) {
            int randomItem = (int) (Math.random() * 3);
            int randomValue = (int) (Math.random() * 499) + 1;
            int randomWeight = (int) (Math.random() * 499) + 1;
            generatedList.add(itemGenerator(randomItem, randomValue, randomWeight));
        }
        return generatedList;
    }

    public Knapsack randomKnapsack() {
        int randomCapacity = (int) (Math.random() * 499) + 1;
        Knapsack knapsack = new Knapsack();
        knapsack.setCapacity(randomCapacity);
        knapsack.setItems(generateItems());
        return knapsack;
    }

    public List<Knapsack> getKnapsacks() {
        for (int i = 0; i < numberOfProblems; i++) {
            listOfKnapsacks.add(randomKnapsack());
        }
        return listOfKnapsacks;
    }

    public void solutionDifferences(Knapsack knapsack) {
        GreedySolution greedySol = new GreedySolution(knapsack, knapsack.getCapacity());
        greedySol.setAvailableItems(knapsack.getItems());
        DynamicProgramming dynamic = new DynamicProgramming(knapsack, knapsack.getCapacity());
        dynamic.setAvailableItems(knapsack.getItems());
        long startTime = System.nanoTime();
        greedySol.solver();
        long endTime = System.nanoTime();
        long greedyExecutionTime = (endTime - startTime);
        System.out.println("Timpul de executie pentru solutia greedy " + greedyExecutionTime + " cu profitul maxim obtinut " + greedySol.getProfit());
        System.out.println();
        long startTimeDP = System.nanoTime();
        dynamic.solver();
        long endTimeDP = System.nanoTime();
        long dynamicExecutionTime = (endTimeDP - startTimeDP);
        System.out.println("Timpul de executie pentru solutia dynamic programming " + dynamicExecutionTime + " cu profitul maxim obtinut " + dynamic.getProfit());
    }
}
