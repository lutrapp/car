package acc.br.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CarTest {
	@Test
	void equalsTest() {
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = null;
		boolean x = car1.equals(car1);
		boolean z = car1.equals(car2);
		boolean y = car1.equals(car3);
		Assertions.assertEquals(true, x);
		Assertions.assertEquals(true, z);
		Assertions.assertEquals(false, y);
	}
}
