import java.util.Scanner;

public class JardimDemo {
    public static void main(String[] args) {
        Jardim jardim = new Jardim();
        System.out.println("Bem-Vindo(A) ao Colheita Feliz!");

        Scanner sc = new Scanner(System.in);
        MenuCommand menuCommand = new MenuCommand(new Jardim());
        while (true) {
            menuCommand.printMenu();
            menuCommand.callSubmenu(sc.next());
        }
    }
}