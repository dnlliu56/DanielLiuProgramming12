//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        for(int i = 1; i < 10; i++)
            System.out.print(main.subtractTwo(i) + " ");
        System.out.print("\n");
        for(int i = 1; i < 10; i++)
            System.out.print(main.timesTwo(i) + " ");
        System.out.print("\n");
        for(int i = 1; i < 10; i++)
            System.out.print(main.doubledFibonacci(i) + " ");
        System.out.print("\n");
        for(int i = 1; i < 10; i++)
            System.out.print(main.tripledFibonacci(i) + " ");
        System.out.print("\n");
        for(int i = 1; i < 10; i++)
            System.out.print(main.growingFactor(i) + " ");
    }
    public int subtractTwo(int term) {
        if (term == 1) {
            return 25;
        }
        else {
            return subtractTwo(term - 1) - 2;
        }
    }
    public int timesTwo(int term) {
        if (term == 1) {
            return 1;
        }
        else {
            return timesTwo(term - 1) * 2;
        }
    }
    public int doubledFibonacci(int term) {
        if (term == 1) {
            return 2;
        } else if (term == 2) {
            return 2;
        } else {
            return doubledFibonacci(term - 1) + doubledFibonacci(term - 2);
        }
    }
    public int tripledFibonacci(int term) {
        if (term == 1) {
            return 3;
        } else if (term == 2) {
            return 3;
        } else {
            return tripledFibonacci(term - 1) + tripledFibonacci(term - 2);
        }
    }
    public int growingFactor(int term) {
        if (term == 1) {
            return 1;
        }
        else {
            return growingFactor(term - 1) * term;
        }
    }
}