package examples.groovy.closures

import examples.groovy.utils.GenericUtils

class MemoizeExample {

	static main(args) {
		def m = new MemoizeExample()
		def n = 40
		println "fib number = $n"
		def gu = new GenericUtils()
		//def time = gu.benchmark{m.runWithoutMemoization(n)}
//		println "Run without memoize took $time seconds"
		
		def time = gu.benchmark {m.runWithMemoization(n)}	
		println "Run with memoize took $time seconds"
		
		//memoizeAtLeast
		//memoizeAtMost
		//memoizeBetween
	}
	
	def runWithoutMemoization (n1) {
		
		int count = 0
		
		def fib
		
		fib = {n ->
			count++
			if(n == 0) 0 
			else if (n == 1) 1
			else {
				fib.call(n-1) + fib.call(n-2)
			}
		}
		
		def actual = fib(n1)
		println "actual = $actual"
		println "count = $count"
	}

	def runWithMemoization (n1) {
		
		int count = 0
				
				def fib
				
				fib = {n ->
				count++
				if(n == 0) 0 
				else if (n == 1) 1
				else {
					fib.call(n-1) + fib.call(n-2)
				}
		}.memoize()
		
		def actual = fib(n1)
				println "actual memoize = $actual"
				println "count memoize = $count"
	}
	
}
