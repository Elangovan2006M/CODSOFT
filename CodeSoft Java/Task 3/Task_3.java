import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: ");
        double balance = scanner.nextDouble();

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            if (option == 1) {
                System.out.println("Current balance: $" + balance);
            } else if (option == 2) {
                System.out.print("Enter deposit amount: ");
                double deposit = scanner.nextDouble();
                if (deposit > 0) {
                    balance += deposit;
                    System.out.println("Deposit of $" + deposit + " successful. Current balance: $" + balance);
                } else {
                    System.out.println("Invalid deposit amount.");
                }
            } else if (option == 3) {
                System.out.print("Enter withdrawal amount: ");
                double withdrawal = scanner.nextDouble();
                if (withdrawal > 0 && withdrawal <= balance) {
                    balance -= withdrawal;
                    System.out.println("Withdrawal of $" + withdrawal + " successful. Current balance: $" + balance);
                } else {
                    System.out.println("Insufficient balance or invalid amount.");
                }
            } else if (option == 4) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
