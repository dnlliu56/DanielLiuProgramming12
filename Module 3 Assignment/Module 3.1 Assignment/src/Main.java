public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int term = 10; // Example term
        System.out.println("The fibonacci number at term " + term + " is " + main.fibonacciNumber(term));
    }

    public int fibonacciNumber(int term) {
        if (term == 0) {
            return 0;
        } else if (term == 1) {
            return 1;
        } else {
            return fibonacciNumber(term - 1) + fibonacciNumber(term - 2);
        }
    }
}