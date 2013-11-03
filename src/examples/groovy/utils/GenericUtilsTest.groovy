package examples.groovy.utils;

import groovy.util.GroovyTestCase;

class GenericUtilsTest extends GroovyTestCase {

	def gu = new GenericUtils()
	
	void testPrepareLineForTokenizingOnComma () {
		//println "Test"
		assert true
		
		assert gu.prepareLineForTokenizingOnComma('No Quotes') == "No Quotes"
		
		assert gu.prepareLineForTokenizingOnComma('Quotes "Around" a Word') == "Quotes ####Around#### a Word"
		
		assert gu.prepareLineForTokenizingOnComma('Quotes "Around","Words"') == "Quotes ####Around####,####Words####"

		assert gu.prepareLineForTokenizingOnComma('Commas "between phrase 1","between phrase 2"') == 'Commas "between phrase 1","between phrase 2"'

		assert gu.prepareLineForTokenizingOnComma('Commas "between phrase 1"    ,"between phrase 2"') == 'Commas "between phrase 1"    ,"between phrase 2"'
		
		assert gu.prepareLineForTokenizingOnComma('Commas "between phrase, 1","between phrase 2"') == 'Commas "between phrase:: 1","between phrase 2"'
		
		assert gu.prepareLineForTokenizingOnComma('Commas "between phrase, 1","between, phrase, 2"') == 'Commas "between phrase:: 1","between:: phrase:: 2"'
	}
}
