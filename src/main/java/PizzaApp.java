import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PizzaApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int maxSlicesToOrder = in.nextInt();
        int types = in.nextInt();
        int[] pizzas = new int[types];
        for(int i = 0; i < types; i++) {
            pizzas[i] = in.nextInt();
        }

        List<Integer> pizzasToOrder = pizzasToOrder(pizzas, maxSlicesToOrder);

        System.out.println(pizzasToOrder.size());
        for (Integer pizza : pizzasToOrder) {
            System.out.print(pizza + " ");
        }
        System.out.println();

        Integer totalSlicesToOrder = pizzasToOrder.stream()
                .map(pizza -> pizzas[pizza])
                .reduce(0, Integer::sum);

        System.err.println(totalSlicesToOrder + " out of " + maxSlicesToOrder);
    }

    public static List<Integer> pizzasToOrder(int[] pizzas, int maxSlicesToOrder) {
        Arrays.sort(pizzas);
        List<Integer> order = new ArrayList<>(pizzas.length);
        for (int i = pizzas.length - 1; i >= 0; i--) {
            int pizza = pizzas[i];
            if (pizza <= maxSlicesToOrder) {
                order.add(i);
                maxSlicesToOrder -= pizza;
            }
        }
        return order;
    }
}
