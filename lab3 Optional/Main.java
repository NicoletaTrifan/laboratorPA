import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*Knapsack rucsac = new Knapsack();
        rucsac.setCapacity(10);
        Knapsack rucsac2 = new Knapsack();
        rucsac2.setCapacity(10);
        Book book1 = new Book("Dragon Rising", 3, 5);
        Book book2 = new Book("A Blade in the Dark", 3, 5);
        Food food1 = new Food("Cabbage", 2);
        Food food2 = new Food("Rabbit", 2);
        Weapon weapon1 = new Weapon(Weapon.WeaponType.SWORD, 5, 10);
        List<Item> availableItems = new ArrayList<>();
        availableItems.add(book1);
        availableItems.add(book2);
        availableItems.add(food1);
        availableItems.add(food2);
        availableItems.add(weapon1);
        System.out.println("Available items : ");
        for (Item item : availableItems) {
            System.out.println(item);
        }
        System.out.println("Solutia greedy ");
        GreedySolution greedySol = new GreedySolution(rucsac, rucsac.getCapacity());
        greedySol.setAvailableItems(availableItems);
        greedySol.solver();
        System.out.println(rucsac);
        System.out.println("Dynamic programming ");
        DynamicProgramming dynamic = new DynamicProgramming(rucsac2, rucsac2.getCapacity());
        dynamic.setAvailableItems(availableItems);
        dynamic.solver();
        System.out.println(rucsac2);*/
        ProblemGenerator problem=new ProblemGenerator(5);
        List<Knapsack> knapsackList=problem.getKnapsacks();
        for(Knapsack knapsack : knapsackList)
        {
            System.out.println(knapsack);
            System.out.println();
            problem.solutionDifferences(knapsack);
            System.out.println();
        }

    }
}
