package examples.groovy.guibuilder

import groovy.swing.SwingBuilder

class GuiPassword {
	
	static main(args){
		def swing = new SwingBuilder()
		def frame = swing.frame(title:'Password'){
			passwordField(columns:10, actionPerformed: {event ->
				println event.source.text
				//any further processing is called here
				System.exit(0)
			})
		}
		frame.pack()
		frame.show()	
	}
}
