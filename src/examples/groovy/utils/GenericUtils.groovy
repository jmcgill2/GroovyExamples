package examples.groovy.utils

class GenericUtils {

	// Returns the execution time in seconds for the code block
	// is passed in.
	public  benchmark(closure) {
		def start = System.currentTimeMillis()
		closure.call()
		def now =  System.currentTimeMillis()
		return (now - start) / 1000F
	}
	
	def prepareLineForTokenizingOnComma(line) {
		def ln
		//first 2 replaceAlls take the commas that are right between 2 quotes (") or seperated from 2 quotes only by spaces and replaces them with 3 semi-colons
		//The third and fourth replaceAlls take the quotes surrounding single words and replace them with ####
		//third replaceAll replaces all the commas between comments with double colons
		//fourth replaceAll replaces all instances of 3 semi-colons with commas, replacing the commas that existed between the double quotes
		ln = line.replaceAll(~/" *,"/, {it.replaceAll(",", ";;;")}).replaceAll(~/"[A-Z a-z]+"/, {it.replaceAll('"', '####')}).replaceAll(~/".*"/,  {it.replaceAll(",", "::")}).replaceAll(';;;',',')
		return ln
	}
	
}
