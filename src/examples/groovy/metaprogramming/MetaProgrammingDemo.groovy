package examples.groovy.metaprogramming

class MetaProgrammingDemo {

	static main(args) {
		def mpd = new MetaProgrammingDemo()
		mpd.expandoMetaClassExample()
	}
	def expandoMetaClassExample() {
		println String.metaClass
		def mc = String.metaClass
		def x = "Test_again"
		String.metaClass.camelCase = {
			delegate = delegate[0].toLowerCase() + ((delegate.size() > 1) ? delegate[1..-1].toLowerCase() : '')
			delegate = delegate.replaceAll(~"((_)([a-z]))", {it[3].toUpperCase()})
		}
		println x.camelCase()
		
		assert "New_Junk".camelCase() == "newJunk"
		println "New_Junk.camelCase() == ${'New_Junk'.camelCase()}"
		println String.metaClass
		String.metaClass = mc
		println String.metaClass
		
	}

}
