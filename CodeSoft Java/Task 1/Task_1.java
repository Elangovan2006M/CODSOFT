import java.util.Random;
import java.util.Scanner;

class Task_1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        while (playAgain) {
            int lowerBound = 1;
            int upperBound = 100;
            int maxAttempts = 7;
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            System.out.println("\nNew Round! Guess the number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts.");

            boolean guessedCorrectly = false;
            int attempts = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    totalScore += attempts;
                    guessedCorrectly = true;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + randomNumber);
            }

            System.out.println("Your current score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score: " + totalScore);
        scanner.close();
    }
}
