package examples.groovy.Strings

class GroovyStringsDemo {

	static main(args) {
	
		def gsd = new GroovyStringsDemo()

		//Groovy Strings with Double Quotes
		gsd.doubleQuoteStringExamples()
		
		gsd.stringConcatenationAndRemoval()
		
		gsd.stringMethods()
		
		//Single Quote Strings in Groovy
		gsd.regularStringExamples()
		
		//Multiple Line Strings
		gsd.multipleLineStringExamples()
		
		def sql = gsd.multipleLineStringRealWorldExample("PGAP500", "ltd_alternate", "Where LTD_ALTERNATE_ID = 5")
		assert sql instanceof java.lang.String
		
		println sql
		assert sql == "select * \n\t\t\t\t\tfrom PGAP500.ltd_alternate \n\t\t\t\t\tWhere LTD_ALTERNATE_ID = 5"
	}
	
	//Strings can be concatenated using + or +=
	//Strings can have text removed using - or -=
	def stringConcatenationAndRemoval() {
		def string1 = "String 1"
		def string2 = "String 2"
		
		assert string1 + string2 == "String 1String 2"
		
		string1 += string2
		assert string1 == "String 1String 2"
		
		//Removing Text from a String
		assert string1 - string2 == "String 1"
		assert string1 - string2 - 'String' == " 1"
	}
	
	//Strings using double quotes are java strings if there are no dynamic values
	//If Dynamic values (identified by either $ or ${}) then the class is a org.codehaus.groovy.runtime.GStringImpl
	def doubleQuoteStringExamples() {
		def doubleQuoteString = "Double Quote String"
		assert doubleQuoteString instanceof java.lang.String
		
		def newVal = "String Again"
		doubleQuoteString = "Double Quote $newVal"
		assert doubleQuoteString instanceof org.codehaus.groovy.runtime.GStringImpl
		assert doubleQuoteString == "Double Quote String Again"
		assert doubleQuoteString.toString() instanceof java.lang.String
	}
	
	//Strings using a single quote (') are java strings and do not convert dynamic values
	def regularStringExamples() {
		def regularString = 'This is a regular string.'
		assert regularString instanceof java.lang.String
		
		def newVal = "irregular string"
		
		regularString = 'This is a $newVal'
		assert regularString instanceof java.lang.String
		assert regularString.contains('$')
		
		assert '"Double Quote"'.contains('"')
	}
	
	
	//Groovy uses 3 quotes (""") to identify multiple line quotes.
	//If the multiple line quote is just text it is a java.lang.String class
	//If the multiple line quote contains dynamic values, the class is an org.codehaus.groovy.runtime.GStringImpl
	def multipleLineStringExamples() {
		
		//Multiple Line Strings in Groovy use three quotes (""") 
		def multipleLineString = """This is the first line
									second line
									third line."""
		
		println multipleLineString
		println multipleLineString.getClass()
		assert multipleLineString == "This is the first line\n\t\t\t\t\t\t\t\t\tsecond line\n\t\t\t\t\t\t\t\t\tthird line."
		assert multipleLineString instanceof java.lang.String

		
		def newVal = "fourth line"
		multipleLineString = """This is the first line
									second line
									$newVal."""
		
		
		println multipleLineString
		println multipleLineString.getClass()
		assert multipleLineString == "This is the first line\n\t\t\t\t\t\t\t\t\tsecond line\n\t\t\t\t\t\t\t\t\tfourth line."
		assert multipleLineString instanceof org.codehaus.groovy.runtime.GStringImpl
		
		//To take a GStringImpl and convert it to a Java String, reference the toString()
		assert multipleLineString.toString() instanceof java.lang.String
		
	}
	
	//One real world example of better 
	def multipleLineStringRealWorldExample(schema, tableName, whereClause) {
		def sql = """select * 
					from ${schema}.${tableName} 
					$whereClause"""

		return sql.toString()
	}

}
