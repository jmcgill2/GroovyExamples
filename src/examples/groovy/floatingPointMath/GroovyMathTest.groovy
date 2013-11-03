package examples.groovy.floatingPointMath;

import groovy.util.GroovyTestCase;

class GroovyMathTest extends GroovyTestCase {
	def gm = new GroovyMath()
	
	void testMultiplyTwoNumbers() {
		assert gm.multiplyTwoNumbers(1.1, 1.1) == 1.21
	}
	
	void testInterpolation() {
		assert gm.interpolation(3, 4, 5, 1, 2) == 1.5
	}
}
