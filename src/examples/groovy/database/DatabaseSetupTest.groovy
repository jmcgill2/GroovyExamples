package examples.groovy.database;

import groovy.util.GroovyTestCase;
import examples.groovy.utils.GenericUtils

class DatabaseSetupTest extends GroovyTestCase {

	def dbs = new DatabaseSetup()
	
	void testStartup() {
		def gu = new GenericUtils()
		def singleRuntime = gu.benchmark {dbs.startup(true)}
		println "singleRuntime = $singleRuntime"
		
		def batchRuntime = gu.benchmark {dbs.startup(false)}
		println "batchRuntime = $batchRuntime"
		
		assert batchRuntime < singleRuntime, "Batch runtime took $batchRuntime and Single run time is $singleRuntime"
		
	}
}
