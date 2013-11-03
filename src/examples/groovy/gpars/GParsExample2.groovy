package examples.groovy.gpars

import static groovyx.gpars.GParsPool.withPool
import jsr166y.ForkJoinPool

class GParsExample2 {

	static main(args) {
		def list = []
		
		for (x in 0..5000) {
			list << x
		}
		
		withPool {
			println "Sequential"
			list.each{print "$it," }
			println ""
			
			println "Concurrent"
			list.makeConcurrent()
			list.each{print "$it,"}
			println ""
		}
	}

}
