package examples.groovy.xml

import java.text.DateFormat;

import examples.groovy.utils.GenericUtils
import groovy.xml.MarkupBuilder
import java.text.SimpleDateFormat
import org.xml.sax.InputSource

import org.xml.sax.InputSource;

class GroovyXml {

	def gu = new GenericUtils()
	
	static main(args) {
		def gx = new GroovyXml()
		//gx.createXmlDocument()
		
//		gx.xmlParser("/tmp/icds_2.xml")
		gx.xmlSlurper("/tmp/icds_2.xml")
	}
	
	def xmlSlurper(xmlFile){
		def file = new File(xmlFile).getText("UTF-8")
		println "file.getClass() = ${file.getClass()}"
		//println file
		def icdsNode = new XmlSlurper().parseText(file)
		println "icdsNode.name() = ${icdsNode.name()}"
		def xs = icdsNode.children()
		println "xs.getClass() = ${xs.getClass()}"
		
		def idx = 0
		println "Before processing children there are ${icdsNode.children().size()} elements."
		icdsNode.children().each{idx++}
		println "idx = $idx"
		println "After processing children there are icdsNode.children.size() = ${icdsNode.children().size()} elements"
	}
	
	def xmlParser(xmlFile) {
		def file = new InputSource(new ByteArrayInputStream(new File(xmlFile).getText().getBytes("UTF-8")))
		println "About to call XmlParser"
		def xml = new XmlParser().parse(file)
		println "Done calling XmlParser"
		
		println "xml.name = ${xml.name()}"
		
		println "size = ${xml.children().size()}"
		def x = xml.children().findAll{it.icd_lng_des.text().contains("Right")}
		//x.each{println it.icd_lng_des.text()}
		println "x.size() = ${x.size()}"
		
		x = xml.children().findAll{it.icd_end_dte.text() < '9999-12-31'}
		x.each{println it.icd_lng_des.text(); println it.icd_end_dte.text()}
		def df = new SimpleDateFormat('yyyy-MM-dd')
		x.each{def d = df.parse(it.icd_end_dte.text()); println "d = $d"  }
		//println "rightIcds = $rightIcds"
	}
	
	def createXmlDocument() {
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		
		def f = getIcdFile()
		
		def icds = builder.icds{
			def idx = 0
			f.eachLine{line -> 
				//println line
				if (idx == 0){
					//Skip column headings line
					idx++
				} else {
					def vals = pullInsertValuesFromString(line)
					icd(){
						icd_id(vals[0])
						icd_cde(vals[1])
						icd_revs_num(vals[2])
						icd_typ_cde(vals[3])
						icd_shrt_des(vals[4])
						icd_lng_des(vals[5])
						icd_strt_dte(vals[6])
						icd_end_dte(vals[7])
					}
				}
			}
		}
		
		//println writer.toString()
		def j = new File("/tmp/icds_2.xml").write(writer.toString())
		
		
	}
	
	def pullInsertValuesFromString(line){
		def ln = gu.prepareLineForTokenizingOnComma(line)
		def tokens = ln.tokenize(",")
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
	
	def getIcdFile(){
		//Get the ICD source file
		def fileName = "${this.class.getResource("/")}com/mutualofomaha/groovy/database/files/icd_demo.csv" - "file:/"
		File f = new File(fileName)
		return f
	}
	
	def tryThis(){
		println 'Test Successful!'
	}

}
