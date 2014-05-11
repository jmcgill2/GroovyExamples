/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples.groovy.basics


/**
 * Contains code examples for basic groovy capabilities.
 * This is another comment.  Adding to check commit and move up to gitbub.
 * 
 * @author jmcgill2
 *
 */
class GroovyBasics {

	static main(args) {
		
		def gb = new GroovyBasics()
		gb.run()
	}
	
	
	/**
	 * 
	 * Run executes all the example methods in this class.
	 */
	def run() {
		noSemiColonsNeeded()
		introToHelperMethods()
		introToDynamicTyping()
	}
	
	/**
	 * provides examples of syntax without semicolons 
	 * 
	 */
	def noSemiColonsNeeded() {
		
		//No semi colons needed
		Integer x = 1
		Integer y = 2
		Integer z = x + y
		
		//Helper methods built into all objects - like println
		println "z = $z"  //"z = $z" is a Groovy String and is not quite like a Java String
		
		//method calls don't require parantheses if an argument is passed, but they can be used
		println("z = $z")
	}
	
	/**
	 * demonstrates list and each
	 */
	def introToHelperMethods() {
		
		//Helper Methods on Groovy Object
		
		//We've already seen println in action - it belongs to the Groovy Object class
		def list = [1, 2, 3, 4, 5, 6,  7, 8, 9, 10]  //This is a list containing the values 1 thru 10
		println "list.getClass() = ${list.getClass()}"  //list is an ArrayList - also not the () following getClass and the ${} around the variable
		
		//Helper method each takes a Closure
		list.each{print it}	//it is a special variable for a variable passed into the closure
		
		//We could also have done this to indicate that a number is being passed in
		list.each{num -> println num}  //println puts each number on a new line
		
		
		//If we were going to add all the Java requirements (assuming Java handled closures) we would do this
		list.each({n -> println "n = $n"});
	
	}
	
	/**
	 * demonstrates dynamic typing
	 */
	def introToDynamicTyping(){ 
		
		//Dynamic Typing allows variables to change at runtime
		def i = 1
		assert i instanceof Integer
		
		i = "2"
		assert i instanceof String
		
		//You can still statically type a variable
		Integer j = 1
			assert j instanceof Integer
		//If you do, you cannot (usually) assign it a value that is not the same type
		try{
			j = "This is a string"
			assert false //We should not get here
		}catch (Exception e){
			assert true //This should fail - but note its a runtime error not a compile error
		}
		
		//But These will work (and you might not expect it)
		try {
			j = "2"
			assert true 
			println "j = $j"  //ascii value for character 2
		} catch (Exception e){
			assert false
		}
		
		//And this will work and you might or might not expect it
		def k = 2.9
		assert k instanceof BigDecimal
		j = k
		println "j = $j"  //j coerces BigDecimal to an Integer rounding down 
		
	}
	
}
