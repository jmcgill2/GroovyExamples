package examples.groovy.database

import examples.groovy.utils.databases.*
import examples.groovy.utils.GenericUtils
import groovy.sql.Sql
import org.h2.Driver

class DatabaseSetup {
	
	def gu = new GenericUtils()

	def startup(single){
		
		//create a Connection
		def conn = Sql.newInstance("jdbc:h2:junk", "sa", "", "org.h2.Driver")
		
		//Drop the table if it exists
		conn.execute("drop table if exists icd".toString())
		
		//Create ICD Table
		conn.execute("create table icd (icd_id integer primary key, icd_cde char(7), icd_revs_num integer, icd_typ_cde char(1), icd_shrt_des char(255), icd_long_des char(500), icd_start_dte date, icd_end_dte date)")
		
		//Load the table and get time it takes to do that
		if (single) {
			loadIcdTableUsingSingleInserts(conn)
		} else {
			loadIcdTablesUsingBatchProcessing(conn, 10000)
		}
	}
	
	def pullInsertValuesFromString(line){
		def tokens = line.tokenize(",")
		//if (printOutput){println "tokens = ${tokens.getClass()}"}
		def vals = []
		tokens.each{t ->
			t = t.replaceAll("::", ",")  //Replace commas between quote marks
			t = t.replaceAll("####", '"') //Replace quotes around words
			t = t.replaceAll("'", "''")  //replace single qoutes with double single quotes because that is hou sql recognizes a quote in the string from a quote surrounding the string
			vals << t

		}
		return vals
	}
	
	def loadIcdTablesUsingBatchProcessing(conn, rowsToProcess) {
		def f = getIcdFile()
		def idx = 0
		def icdInsertList = []
		f.eachLine{line ->
			if (idx == 0){
			   idx++
			   //Do nothing - the first file line has category column names
			} else {
				if (line.contains('"')) { //we may have commas that we should not tokenize on
					line = gu.prepareLineForTokenizingOnComma(line)
				}
				def values = pullInsertValuesFromString(line)
				icdInsertList << values

			}
		}
		
		def sql = 'insert into icd (icd_id, icd_cde, icd_revs_num, icd_typ_cde, icd_shrt_des, icd_long_des, icd_start_dte, icd_end_dte) values (?, ?, ?, ?, ?, ?, ?, ?)'
		conn.withBatch(100000, sql, {ps -> 
			icdInsertList.each{val ->  ps.addBatch(val)}
		})
		
		def cnt = conn.firstRow("select count(*) as cnt from icd").cnt
		println "cnt = $cnt"
	}
	
	def getIcdFile(){
		//Get the ICD source file
		def fileName = "${this.class.getResource("/")}com/mutualofomaha/groovy/database/files/icd_demo.csv" - "file:/"
		File f = new File(fileName)
		return f
	}
	
	def loadIcdTableUsingSingleInserts(conn){
		
		def f = getIcdFile()
		def idx = 0
		f.eachLine{line ->
			if (idx == 0){
			   idx++
			   //Do nothing - the first file line has category column names
			} else {
				if (line.contains('"')) { //we have commas that we should not tokenize on
					line = gu.prepareLineForTokenizingOnComma(line)
				}
				def values = pullInsertValuesFromString(line)
				def sql = "insert into icd values (${values[0]}, '${values[1]}', ${values[2]}, '${values[3]}', '${values[4]}', '${values[5]}', '${values[6]}', '${values[7]}')".toString()
				conn.executeInsert(sql)

			}
		}
		
		def cnt = conn.firstRow("select count(*) as cnt from icd").cnt
		println "cnt = $cnt"

	}
}
