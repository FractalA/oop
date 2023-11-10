package lab1;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        if (numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*?)\\\\n");
            Matcher matcher = pattern.matcher(numbers);
            if (matcher.find()) {
                String customDelimiter = matcher.group(1);
                if (customDelimiter.length() != 1 || numbers.startsWith(customDelimiter) || numbers.endsWith(customDelimiter)) {
                    throw new IllegalArgumentException("Помилка: недійсний кастомний роздільник.");
                }
                regex = Pattern.quote(customDelimiter) + "|\\\\n|,";
                numbers = numbers.substring(matcher.end());
            }
        }

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
