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

package examples.groovy.closures

/**
 * Provides examples of groovy closures
 * @author jmcgill2
 *
 */
class ClosureCompositionExample {

	static main(args) {
		def c = new ClosureCompositionExample()
		c.run()
	}
	
	/**
	 * examples of closures assigned to variables and then concatenated
	 */
	def run() {
		def plus2 = {it + 2}
		def times3 = {it * 3}
		
		def times3Plus2 = times3 << plus2
		
		assert times3Plus2(2) == 12
		println "times3Plus2(2) = ${times3Plus2(2)}"
	}

}
