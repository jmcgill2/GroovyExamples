package examples.groovy.files

class GroovyFileDemo {

	static main(args) {
		
		//Creating a file is as simple as this (assumes tmp directory already exists)
		def f = new File("c:/tmp/gfile.txt")
		
		//Previous step does not actually create a file - it just prepares for file processing
		assert f.exists() == false
		
		//To write output to a file in a simple way  one line at a time (but not very efficiently)
		f << "Test\n"
		f.append("Test2\n")  //Same as <<
		assert f.exists() == true
		
		assert f.text == "Test\nTest2\n"
		
		//if you append - you don't overwrite a file - even with a new file variable
		def input = new File("c:/tmp/gfile.txt")
		input.append("Junk\n")
		assert input.text == "Test\nTest2\nJunk\n"
		
		//Using write to write to replace contents of a file with new content
		input.write("New Content\n")
		assert input.text == "New Content\n"
		
		input.write("Extra Content\n")
		assert input.text == "Extra Content\n"
		assert f.text == "Extra Content\n"  //Verifies that the file has changed - not just the input version
		input.append("Second Line\n")  
		
		//Reading a file is easy
		
		//To read entire text file at once
		def fileText = new File("c:/tmp/gfile.txt").text
		assert fileText == "Extra Content\nSecond Line\n"
		
		//To process a text file one line at a time
		def list = []
		input.eachLine{list << it}
		assert list.size() == 2
		
		//To replace the contents of a text file is quite easy
		input.write(input.text.replaceAll("Line", "Ghost"))
		assert f.text == "Extra Content\nSecond Ghost\n"
		
		//To delete a file 
		f.delete()
		assert f.exists() == false
		assert input.exists() == false
		
		
	
		//dirSearch()
		//Add an Update example
		
		//Add a writer example
		
		//Add a copy example
		
	}
	
	
	static dirSearch() {
		def f = new File("c:/")
		def largestFileName = ""
		def largestFileSize = 0
		def totalNumberOfFiles = 0
		
		f.eachDirRecurse{dir ->
			dir.eachFile{file ->
				if (file.isFile()){
					totalNumberOfFiles++
					if (file.size() > largestFileSize){
						largestFileSize = file.size()
						largestFileName = file.canonicalPath
					}
				}
			}
		}
		println "Total Number of Files = $totalNumberOfFiles"
		println "Largest File is $largestFileName and is $largestFileSize big"
	}

}
