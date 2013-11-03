package examples.groovy.floatingPointMath

class GroovyMath {

	static main(args) {

		def gm = new GroovyMath()	
		def x = 1.1
		def y = 1.1
		
		println "x * y = ${gm.multiplyTwoNumbers(x, y)}"
		assert x * y == 1.21
		assert x instanceof java.math.BigDecimal
		assert y instanceof java.math.BigDecimal
		
		def x1 = 3
		def x2 = 4
		def x3 = 5
		def y1 = 1
		def y3 = 2
		gm.interpolation(x1, x2, x3, y1, y3)
	}
	
	def multiplyTwoNumbers(a, b){
		return a * b
	}
	
	def interpolation (x1, x2, x3, y1, y3){
		
		def y2 = (( (x2 - x1) * (y3 - y1) )/(x3 - x1))  + y1
		return y2
	}

}
