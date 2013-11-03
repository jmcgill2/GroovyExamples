package examples.groovy.threads

import examples.groovy.utils.GenericUtils
import groovy.sql.Sql
import org.h2.Driver

class GroovyThreadingDemo {

	static main(args) {
		//create a list of integers
		def maxNum = 800000
		def list = []
		for (x in 1..maxNum){list << x}
		assert list.size() == maxNum
		
			
		def gu = new GenericUtils()
		
		//create a database Connection
		def conn = Sql.newInstance("jdbc:h2:junk", "sa", "", "org.h2.Driver")
		
		//Drop the table if it exists
		conn.execute("drop table if exists Int_load".toString())
		
		//Create ICD Table
		conn.execute("create table int_load (icd_id integer primary key)")
		
		def singleLoadTime = gu.benchmark{ 
			list.each{conn.execute("insert into int_load (icd_id) values($it)")}
		}
				
		def cnt = conn.firstRow("select count(*) as cnt from int_load").cnt
		println "It took $singleLoadTime seconds to load $cnt records"
		
//		def deleteTime = gu.benchmark {conn.execute("delete from int_load")}
//		
//		println "delete time is $deleteTime"
		
		def singleProcessTime = gu.benchmark{
			def tv = 0
			list.each{
				def row = conn.firstRow("select icd_id from int_load where icd_id = $it").icd_id
				if (row < 50000){
					tv = tv + 1
				}else if (row < 100000){
					tv = tv+2
				} else {
					tv = tv+3
				}
			}
			println "single process tv = $tv"
		}
		
		println "singleProcessTime is $singleProcessTime"
		def miniLists = list.collate(80000)
		assert list.size() == maxNum
		assert miniLists.size() == 10
		
		miniLists.each{ml ->
			Thread.start {
				def c = Sql.newInstance("jdbc:h2:junk", "sa", "", "org.h2.Driver")
				def tv = 0
				def gut = new GenericUtils()
				def threadTime = gut.benchmark{
					ml.each{
						def row = c.firstRow("select icd_id from int_load where icd_id = $it").icd_id
						if (row < 50000){
							tv = tv + 1
						}else if (row < 100000){
							tv = tv+2
						} else {
							tv = tv+3
						}
					}
				}
				println "Thread ${Thread.currentThread().getName()} tv = $tv and did it in $threadTime seconds"
			}
		}
		
		
	}

}
