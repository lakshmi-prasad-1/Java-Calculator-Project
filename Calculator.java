import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char c;
        do {
            System.out.println("\n1.Add  2.Sub  3.Mul  4.Div  5.Sqrt  6.Pow  7.Temp  8.Currency");
            System.out.print("Choose (1-8): ");
            try {

                Integer choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        add();
                        break;
                    case 2:
                        sub();
                        break;
                    case 3:
                        mul();
                        break;
                    case 4:
                        div();
                        break;
                    case 5:
                        sqrt();
                        break;
                    case 6:
                        pow();
                        break;
                    case 7:
                        tempConv();
                        break;
                    case 8:
                        currConv();
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            } catch (ArithmeticException e) {
                System.out.println("Math Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred!");
            }

            System.out.print("\nContinue? (y/n): ");
            c = sc.next().charAt(0);
        } while (c != 'n' && c != 'N');
        System.out.println("Goodbye!");
    }

    static BigDecimal get(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return sc.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number!");
                sc.nextLine();
            }
        }
    }

    static void add() {
        System.out.println("Result: " + get("Num1: ").add(get("Num2: ")));
    }

    static void sub() {
        System.out.println("Result: " + get("Num1: ").subtract(get("Num2: ")));
    }

    static void mul() {
        System.out.println("Result: " + get("Num1: ").multiply(get("Num2: ")));
    }

    static void div() {
        System.out.println("Result: " + get("N1: ").divide(get("N2: "), MathContext.DECIMAL64));
    }

    static void sqrt() {
        BigDecimal n = get("Num: ");
        if (n.signum() < 0)
            throw new ArithmeticException("Negative SQRT!");
        System.out.println("Result: " + Math.sqrt(n.doubleValue()));
    }

    static void pow() {
        BigDecimal b = get("Base: ");
        System.out.print("Exp (int): ");
        System.out.println("Result: " + b.pow(sc.nextInt(), MathContext.DECIMAL64));
    }

    static void tempConv() {
        System.out.print("1. C to F   2. F to C\nChoose: ");
        int type = sc.nextInt();
        BigDecimal temp = get("Enter temperature: ");
        if (type == 1) {
            BigDecimal f = temp.multiply(new BigDecimal("1.8")).add(new BigDecimal("32"));
            System.out.println("Result: " + f + " F");
        } else if (type == 2) {
            BigDecimal c = temp.subtract(new BigDecimal("32")).divide(new BigDecimal("1.8"), MathContext.DECIMAL64);
            System.out.println("Result: " + c + " C");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    static void currConv() {
        System.out.println("1. USD to EUR     2. EUR to USD");
        System.out.print("3. USD to INR     4. INR to USD\nChoose: ");
        int type = sc.nextInt();
        BigDecimal amt = get("Enter amount: ");
        if (type == 1) {
            System.out.println(
                    "Result: " + amt.multiply(new BigDecimal("0.92")).setScale(2, RoundingMode.HALF_UP) + " EUR");
        } else if (type == 2) {
            System.out.println(
                    "Result: " + amt.multiply(new BigDecimal("1.09")).setScale(2, RoundingMode.HALF_UP) + " USD");
        } else if (type == 3) {
            System.out.println(
                    "Result: " + amt.multiply(new BigDecimal("83.50")).setScale(2, RoundingMode.HALF_UP) + " INR");
        } else if (type == 4) {
            System.out.println(
                    "Result: " + amt.divide(new BigDecimal("83.50"), 2, RoundingMode.HALF_UP) + " USD");
        } else {
            System.out.println("Invalid choice!");
        }
    }
}
