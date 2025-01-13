import java.util.Scanner;

public class Calculator {
    int add(int i, int j) {
        return i + j;
    }

    int subtract(int i, int j) {
        return i - j;
    }

    int multiply(int i, int j) {
        return i * j;
    }

    int divide(int i, int j) {
        if (j == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        return i / j;
    }

    public int calculate(String input) {

        if(input == null){
            return 0;
        }

        input = input.replaceAll("\\s+", "");
        validateInput(input);

        if(input.isEmpty()){
            return 0;
        }

        String[] values = input.split("(?=[-+*/,:])|(?<=[-+*/,:])");
        int result = Integer.parseInt(values[0]);

        for (int i = 1; i < values.length; i += 2) {
            String operator = values[i];
            int nextNumber = Integer.parseInt(values[i + 1]);

            switch (operator) {
                case "+":
                case ",":
                case ":":
                    result = add(result, nextNumber);
                    break;
                case "-":
                    result = subtract(result, nextNumber);
                    break;
                case "*":
                    result = multiply(result, nextNumber);
                    break;
                case "/":
                    result = divide(result, nextNumber);
                    break;
                default:
                    throw new IllegalArgumentException("유효하지 않은 연산자: " + operator);
            }
        }

        return result;
    }

    private void validateInput(String input) {
        String[] values = input.split("(?=[-+*/,:])|(?<=[-+*/,:])");
        if (values.length % 2 == 0) {
            throw new IllegalArgumentException("유효하지 않은 요청입니다. Format: <number> <operator> <number>");
        }

        if(values.length == 1 && values[0].equals("")){
            return;
        }


        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) { // 숫자 위치
                try {
                    Integer.parseInt(values[i]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("유효하지 않은 요청입니다: " + values[i]);
                }
            } else { // 연산자 위치
                if (!values[i].matches("[+\\-*/,:]")) {
                    throw new IllegalArgumentException("유효하지 않은 연산자입니다: " + values[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Calculator calculator = new Calculator();
        try {
            int result = calculator.calculate(input);
            System.out.println("결과: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("에러: " + e.getMessage());
        }
    }
}
