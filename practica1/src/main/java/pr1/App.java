package pr1;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void sumapares() {
        Scanner scanf = new Scanner(System.in);
        System.out.print("Numero de pares que desea: ");
        int n = scanf.nextInt();
        int sum = n*(n+1);
        System.out.println(sum);
        scanf.close();
    }

    public static void main(String[] args) {
        sumapares();
    }
}
