package examples.groovy.guibuilder

import groovy.swing.SwingBuilder

class JunkBuilder {

	static main(args) {
		def swing = new SwingBuilder()
//		def frame = swing.frame(title:'Demo'){
//			menuBar {
//				menu('File') {
//					menuItem 'New'
//					menuItem 'Open'
//				}
//			}
//			panel {
//				label 'Label 1'
//				slider()
//				comboBox(items:['one', 'two', 'three'])
//			}
//		}
		
//		def printAction = swing.action(name:'Print', closure: {
//			println swing.message.text
//		})
//		
//		def frame = swing.frame(title:'Printer') {
//			panel {
//				textField(action:printAction, id:'message', columns:10)
//				button (action:printAction)
//			}
//		}
		
		def data = [
				[nick:'MrG',			full:'Guillaume Laforge'],
				[nick:'jez',			full:'Jeremy Rayner'],
				[nick:'fraz',			full:'Franck Rasalo'],
				[nick:'sormuras', 		full:'Christian Stein'],
				[nick:'blackdrag',		full:'Jochen Theodorou'],
				[nick:'Mittie',			full:'Dierk Koenig']		
			]
		
		def frame = swing.frame(title:'Demo') {
			scrollPane {
				table() {
					tableModel(list:data) {
						propertyColumn(header:'Nickname', propertyName:'nick')
						propertyColumn(header:'Full Name', propertyName:'full')
					}
				}
			}
		}
		frame.pack()
		frame.show()
		
	}

}
