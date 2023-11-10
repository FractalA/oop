package lab1;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> delimiters = Arrays.asList(",", "\\n");

        for (String delimiter : delimiters) {
            if (numbers.startsWith(delimiter)) {
                throw new IllegalArgumentException("помилка: недійсний рядок");
            }
        }

        for (String delimiter : delimiters) {
            if (numbers.endsWith(delimiter)) {
                throw new IllegalArgumentException("помилка: недійсний рядок");
            }
        }

        String regex = String.join("|", delimiters) + "|\\\\n";
        String[] numArr = numbers.split(regex);

        int res = 0;
        for (String num : numArr) {
            num = num.trim();

            if (num.isEmpty()) {
                throw new IllegalArgumentException("помилка: недійсний рядок");
            }

            try {
                int x = Integer.parseInt(num);
                res += x;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("помилка: недійсний рядок");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("введіть рядок: ");
        String input = scanner.nextLine();
        int res = Add(input);
        System.out.println("результат: "+res);
        scanner.close();
    }
}
