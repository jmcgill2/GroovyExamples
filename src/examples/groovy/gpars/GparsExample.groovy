package examples.groovy.gpars

import static groovyx.gpars.GParsPool.withPool
import jsr166y.ForkJoinPool

class GparsExample {

	static main(args) {
		def g = new GparsExample()
		g.run()
	}
	
	def run() {
		def nums = 1..10000000
		
		withPool(5) {
			def squares = nums.
				collectParallel{it ** 2 }.
				grepParallel{it % 7 == it % 5}.
				grepParallel{it % 3 == 0}
			println "squares.size() = ${squares.size()}"
			println squares[0..3] + "..." + squares[-3..-1]
			
		}
		
	}
	
}