/**
 * 
 */
package examples.groovy.gpars

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

/**
 * @author jmcgill2
 *
 */
class GameMaster extends DefaultActor {

	int secretNum
	boolean winnerFound
	int ctr = 1
	
	void afterStart() {
		secretNum = new Random().nextInt(1000000)
		println "The secret Number to guess is $secretNum"
	}
	
	void onTimeout() {
		terminate()
	}
	
	void act() {
		loop {
			ctr++
			react 1000, {int num ->
				println "processing number $num"
				if (winnerFound) {
					reply "you lose!"
				}
				if (num > secretNum) {
					reply 'too large'
				} else if (num < secretNum){
					reply "too small"
				} else {
					reply "you win!"
					winnerFound = true
				}
			}
		}
	}
}
