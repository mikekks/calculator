import static org.junit.jupiter.api.Assertions.*;

import junit.framework.TestCase;

public class CalculatorTest2 extends TestCase {
	Calculator cal;

	protected void setUp()  {
		cal = new Calculator();
	}

	public void test_덧셈()  {
		assertEquals(7, cal.add(3, 4));
	}

	protected void tearDown() {
		cal = null;
	}
}
