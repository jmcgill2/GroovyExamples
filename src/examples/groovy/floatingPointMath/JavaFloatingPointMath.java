package examples.groovy.floatingPointMath;

import java.math.BigDecimal;

public class JavaFloatingPointMath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JavaFloatingPointMath jfpm = new JavaFloatingPointMath();
		
		double x = 1.1;
		double y = 1.1;
		System.out.println("1.1 * 1.1 = " + jfpm.multipyTwoDoubles(x, y));
		
		//To Use BigDecimal
		BigDecimal bdx = new BigDecimal(1.1);
		BigDecimal bdy = new BigDecimal(1.1);
		
		System.out.println ("Big Decimal x using double 1.1 = " + bdx);
		System.out.println ("Big Decimal y  using double 1.1 = " + bdy);
		
		bdx = new BigDecimal("1.1");
		bdy = new BigDecimal("1.1");
		
		System.out.println ("Big Decimal x using String '1.1' = " + bdx);
		System.out.println ("Big Decimal y = using String '1.1' = " + bdy);
		
		System.out.println ("Big Decimal bdx.multiply(bdy) = " + bdx.multiply(bdy));
		
		jfpm.interpolationCheck();
		
	}
	
	public double multipyTwoDoubles(double a, double b){
		return a * b;
	}
	
	public void interpolationCheck (){
		BigDecimal x1 = new BigDecimal("3");
		BigDecimal x2 = new BigDecimal("4");
		BigDecimal x3 = new BigDecimal("5");
		BigDecimal y1 = new BigDecimal("1");
		BigDecimal y3 = new BigDecimal("2");
	
		assert interpolation(x1, x2, x3, y1, y3).equals(1.5);
	}
	
	public BigDecimal interpolation(BigDecimal x1, BigDecimal x2, BigDecimal x3, BigDecimal y1, BigDecimal y3) {
		//y2 = (( (x2 - x1) * (y3 - y1) )/(x3 - x1))  + y1
		BigDecimal y2 = x2.subtract(x1).multiply((y3.subtract(y1))).divide((x3.subtract(x1))).add(y1);
		return y2;

	}
	
	


}
