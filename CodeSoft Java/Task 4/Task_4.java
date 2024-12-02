import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Task_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter!");
        System.out.println("Available Currencies: USD, EUR, INR, GBP, JPY");

        System.out.print("Enter Base Currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Target Currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().trim().toUpperCase();

        if (baseCurrency.equals(targetCurrency)) {
            System.out.println("Base and Target currencies are the same. No conversion needed.");
            return;
        }

        System.out.print("Enter Amount to Convert: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for amount. Please enter a numeric value.");
            return;
        }

        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error: Unable to fetch exchange rate. Please try again later.");
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner responseScanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (responseScanner.hasNext()) {
            response.append(responseScanner.nextLine());
        }
        responseScanner.close();
        connection.disconnect();

        String jsonResponse = response.toString();

        int targetIndex = jsonResponse.indexOf("\"" + targetCurrency + "\":");
        if (targetIndex == -1) {
            throw new Exception("Target currency not found.");
        }

        int startIndex = targetIndex + targetCurrency.length() + 3;
        int endIndex = jsonResponse.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonResponse.indexOf("}", startIndex);
        }

        String rateString = jsonResponse.substring(startIndex, endIndex).trim();
        return Double.parseDouble(rateString);
    }
}
