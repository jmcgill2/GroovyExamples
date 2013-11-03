package examples.groovy.gpars

class Game {

	static main(args) {
		def master = new GameMaster().start()
		def player = new Player(name:'JIM', server:master).start()
		def player2 = new Player(name:'SIERRA', server:master).start()
		def player3 = new Player(name:"TAMI", server:master).start()
		def player4 = new Player(name:"STEVEN", server:master).start()
		
//		[master, player]*.join()
		
		[master, player, player2, player3]*.join()
		
	}

}
