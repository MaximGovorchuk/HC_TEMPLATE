import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

        List<Integer> pizzasToOrder = randomPizzasToOrder(pizzas, maxSlicesToOrder);

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

    public static List<Integer> randomPizzasToOrder(final int[] pizzas, final int maxSlicesToOrder) {
        int[] pizzaIds = new int[pizzas.length];
        for (int i = 0; i < pizzas.length; i++) {
            pizzaIds[i] = i;
        }

        List<Integer> bestOrder = Collections.emptyList();
        int bestOrderLeftOver = Integer.MAX_VALUE;

        for(int times = 0; times < 10000; times++) {
            shuffleArrayFisherYates(pizzaIds);
            int slicesLeft = maxSlicesToOrder;
            List<Integer> order = new ArrayList<>(pizzaIds.length);
            for (int i = pizzaIds.length - 1; i >= 0; i--) {
                int chosen = pizzaIds[i];
                int pizzaSlices = pizzas[chosen];
                if (pizzaSlices <= slicesLeft) {
                    order.add(chosen);
                    slicesLeft -= pizzaSlices;
                }
            }
            if (bestOrderLeftOver > slicesLeft) {
                bestOrderLeftOver = slicesLeft;
                bestOrder = order;
            }
        }
        return bestOrder;
    }

    private static void shuffleArrayFisherYates(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
