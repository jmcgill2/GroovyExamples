package examples.groovy.gpars

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

class Player extends DefaultActor {

	String name
	Actor server
	int myNum
	
	void act() {
		loop {
			myNum = new Random().nextInt(1000000)
			server.send myNum
			react {
				switch(it) {
					case 'too large':
						println "$name: $myNum was too large"
						break
					case "too small":
						println "$name: $myNum was too small"
						break
					case "you win!":
						println "$name: I won $myNum"
						terminate()
						break
					case "you lose!":
						println "$name: Someone else already won."
						terminate()
						break
				}
			}
		}
	}
}
