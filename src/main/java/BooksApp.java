import java.util.Scanner;

public class BooksApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int booksRequired = in.nextInt();
        int librariesNumber = in.nextInt();
        int daysDeadline = in.nextInt();

        int[] bookScores = new int[booksRequired];
        for(int i = 0; i < booksRequired; i++) {
            bookScores[i] = in.nextInt();
        }

        Library[] libraries = new Library[librariesNumber];

        for
    }


}
