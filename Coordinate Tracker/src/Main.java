import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        //prompts user for coordinates
        System.out.println("What is the x value?");
        String xValue = userInput.nextLine();
        System.out.println("What is the y value?");
        String yValue = userInput.nextLine();

        //converts string input into double
        double xDouble = Double.parseDouble(xValue);
        double yDouble = Double.parseDouble(yValue);

        //logical deduction
        if (xDouble > 0 && yDouble > 0) {
            System.out.println("Your point is in the 1st quadrant!");
        }
        else if (xDouble < 0 && yDouble > 0) {
            System.out.println("Your point is in the 2nd quadrant!");
        }
        else if (xDouble < 0 && yDouble < 0) {
            System.out.println("Your point is in the 3rd quadrant!");
        }
        else if (xDouble > 0 && yDouble < 0) {
            System.out.println("Your point is in the 4th quadrant!");
        }
        else {
            System.out.println("Your point is not in a quadrant!");
        }
    }
}