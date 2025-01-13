import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

	private final Calculator calculator = new Calculator();

	@Test
	void testAddWithOverflow() {
		int max = Integer.MAX_VALUE;
		int result = calculator.add(max, 1);
		assertThat(result).isEqualTo(Integer.MIN_VALUE);
	}

	@Test
	void testAddWithNegativeOverflow() {
		int min = Integer.MIN_VALUE;
		int result = calculator.add(min, -1);
		assertThat(result).isEqualTo(Integer.MAX_VALUE);
	}

	// subtract() 메서드 실패 케이스
	@Test
	void testSubtractWithOverflow() {
		int min = Integer.MIN_VALUE;
		int result = calculator.subtract(min, 1);
		assertThat(result).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	void testSubtractWithNegativeOverflow() {
		int max = Integer.MAX_VALUE;
		int result = calculator.subtract(max, -1);
		assertThat(result).isEqualTo(Integer.MIN_VALUE);
	}

	// multiply() 메서드 실패 케이스

	@Test
	void testMultiplyWithZero() {
		int result = calculator.multiply(1000, 0);
		assertThat(result).isEqualTo(0);
	}

	// divide() 메서드 실패 케이스
	@Test
	void testDivideByZero() {
		assertThatThrownBy(() -> calculator.divide(5, 0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("0으로 나눌 수 없습니다.");
	}

	@Test
	void testDivideWithNegativeZero() {
		int result = calculator.divide(-10, -2);
		assertThat(result).isEqualTo(5);
	}

	// calculate() 메서드 실패 케이스
	@Test
	void testCalculateWithInvalidFormat() {
		assertThatThrownBy(() -> calculator.calculate("1 + , 2"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 요청입니다. Format: <number> <operator> <number>");
	}

	@Test
	void testCalculateWithInvalidOperator() {
		assertThatThrownBy(() -> calculator.calculate("1 ^ 2"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 요청입니다: 1^2");
	}

	@Test
	void testCalculateWithOnlyNumbers() {
		int calculate = calculator.calculate("10 20");
		assertThat(calculate).isEqualTo(1020);
	}

	@Test
	void testCalculateWithValidInput() {
		int result = calculator.calculate("10 + 5");
		assertThat(result).isEqualTo(15);
	}

	// validateInput() 메서드 실패 케이스
	@Test
	void testValidateInputWithNull() {
		int result = calculator.calculate(null);
		assertThat(result).isEqualTo(0);
	}

	@Test
	void testValidateInputWithInvalidOperator() {
		assertThatThrownBy(() -> calculator.calculate("5 ^ 3"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 요청입니다: 5^3");
	}

	@Test
	void testValidateInputWithOddNumberOfValues() {
		assertThatThrownBy(() -> calculator.calculate("5 +"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않은 요청입니다. Format: <number> <operator> <number>");
	}

	@Test
	void testValidateInputWithEmptyString() {
		int result = calculator.calculate("");
		assertThat(result).isEqualTo(0);
	}
}

