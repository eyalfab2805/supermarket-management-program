package TomBitran_Eyal_Fabian;

public class InputValidator {
    public static int validateInt(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input. Please enter a valid integer.");
        }
    }

    public static double validateDouble(String input) throws InvalidInputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input. Please enter a valid double.");
        }
    }
}
