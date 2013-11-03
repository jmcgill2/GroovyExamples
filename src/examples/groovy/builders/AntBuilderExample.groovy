package examples.groovy.builders

class AntBuilderExample {

	static main(args) {
		def docsDirectory = "dist"
		def mainSourceDirectory = "src"
		def docFooter = "Groovy Examples groovyDocs generated on ${new Date()} "
		def title = "Groovy Examples"
		AntBuilder ab = new AntBuilder()
		ab.echo("starting")
		ab.taskdef(name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc")
		ab.groovydoc(
			destdir      : "${docsDirectory}/gapi",
			sourcepath   : "${mainSourceDirectory}",
			packagenames : "**.*",
			use          : "true",
			windowtitle  : "${title}",
			doctitle     : "${title}",
			header       : "${title}",
			footer       : "${docFooter}",
//			overview     : "src/main/overview.html",
			private      : "false") {
				link(packages:"java.,org.xml.,javax.,org.xml.",href:"http://download.oracle.com/javase/6/docs/api")
				link(packages:"groovy.,org.codehaus.groovy.",  href:"http://groovy.codehaus.org/api")
				link(packages:"org.apache.tools.ant.",         href:"http://evgeny-goldin.org/javadoc/ant/api")
				link(packages:"org.junit.,junit.framework.",   href:"http://kentbeck.github.com/junit/javadoc/latest")
				link(packages:"org.codehaus.gmaven.",          href:"http://evgeny-goldin.org/javadoc/gmaven")
		}
		ab.echo("Done")
	}

}
