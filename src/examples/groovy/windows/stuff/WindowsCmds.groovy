package examples.groovy.windows.stuff

class WindowsCmds {

	static main(args) {
	
		def cmds = [ ]
		def j = "cmd /c dir " + /\boids/
		//def bt = "cmd /c dir c:\\"
//		cmds << j
		//cmds << bt
		
//		def x = bt.execute().text
		cmds.each{c ->
			def x = c.execute().text
			println "x = $x"
		}
		
		def js = ['cmd', '/c', 'dir']
		def dir = /\Program Files/
		def y = (js + dir).execute()
		//println "y = ${y.text}"
		
		def jx = ['cmd', '/c', 'echo %PATH%']
		println jx.execute().text
		
		def j1 = 'cmd /c date /t'.execute().text.split(/\D/)
		println "j1 = $j1"
//		println j1.join('-').replaceAll('--','')
		
		def proc = 'cmd /c date'.execute()
		
		Thread.start {System.out << proc.in }
		Thread.start {System.err << proc.err }
		
		proc << 'no-such-date' + '\n'
		
		proc.out.close()
		proc.waitForOrKill(10000)
		
	}

}
