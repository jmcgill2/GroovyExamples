package examples.groovy.floatingPointMath;

import java.math.BigDecimal;

import groovy.util.GroovyTestCase;

class JavaFloatingPointMathTest extends GroovyTestCase {
	
	def jfpm = new JavaFloatingPointMath()
	
	void testMultipyTwoDoubles() {
		assert jfpm.multipyTwoDoubles(1.1, 1.1) != 1.21
	}
	
	void testInterpolation() {
		BigDecimal x1 = new BigDecimal("3");
		BigDecimal x2 = new BigDecimal("4");
		BigDecimal x3 = new BigDecimal("5");
		BigDecimal y1 = new BigDecimal("1");
		BigDecimal y3 = new BigDecimal("2");

		assert jfpm.interpolation(x1, x2, x3, y1, y3) == 1.5
	}

}
