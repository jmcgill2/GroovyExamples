package examples.groovy.collections

class GroovyCollectionsDemo {

	static main(args) {
	
		def list = []  //Creating an empty list
		
		for (x in 0..10){ list << x}  //assigning values to a list
		assert list.size() == 11
		
		def y = list.find{it == 4}  //find returns 1 value or none
		assert y == 4
		y = list.find{it == 12}
		assert y == null
		
		y = list.find{it < 4}  //find returns the first value
		assert y == 0
		
		y = list.find{it == 12}  //find all returns null if nothing found
		assert y == null
		
		y = list.findAll{it == 4}  //find all returns a list with all the values
		assert y == [4]
		
		
		y = list.findAll{it == 12}  //find all returns empty list if search fails 
		assert y == []
		
		y = list.findAll{it < 4}  //find all returns list of all values meeting search criteria
		assert y == [0, 1, 2, 3]
		
		//spreadback or collection call
		assert list.collect {it * 2} == [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
		
		//sum
		assert list.sum() == 55  //Note previous use of collect did not change existing list
	
		
		//Sorting 
		assert list.reverse() == [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]  //reverse
		
		def names = ['Jill', 'Jack', 'Adam']
		assert names.sort() == ['Adam', 'Jack', 'Jill']
		
		//sorting results of a findAll
		assert names.findAll{it.contains('a')}.reverse() == ['Jack','Adam']
		
		//sorting ascending on name and descending on date
		def sales = [ [buyer:"Joe", amt:100.00, day:new Date()],
					  [buyer:"Joe", amt:50.00, day: new Date() + 1],
					  [buyer:"Mike", amt:75.00, day: new Date()],
					  [buyer:'Adam', amt:400.00, day: new Date() - 10],
					  [buyer:'Adam', amt:1.00, day: new Date() - 14],
					]
		
		//println "Sales"
		//sales.each{println it}

		//Sort on buyer in ascending order and date in descending order
		// <=> returns 0 if equal so we will compare date if both buyer names are the same
		// ?: a/k/a the elvis operator - returns the first value tested if true otherwise returns 
		//          the second value (in this case b.day <=> a.day
		sales.sort {a, b -> a.buyer <=> b.buyer ?: b.day <=> a.day} 
		
//		println "Sorted Sales"
//		sales.each{println it}
		
		def buyerTotals = []
		sales.groupBy{it.buyer}each{buyer, purchases -> 
			def totalPurchases = 0
			purchases.each{purchase -> totalPurchases += purchase.amt}
			buyerTotals << [buyer:buyer, amt:totalPurchases]  //key buyer is string buyer, value buyer Adam, etc.
		}
		//sort in descending order and print
		buyerTotals.sort({buyer1, buyer2 -> buyer2.amt <=> buyer1.amt}).each{println "${it.buyer} spent ${it.amt}"}
		
	}
	
}
