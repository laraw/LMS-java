/*
 * OUA CPT221, SP1 2014, Model Test Data Utility
 */
package lms.view.util;

import lms.model.Holding;
import lms.model.Book;
import lms.model.Video;
import lms.model.facade.LMSModel;

public class Tester {
	private LMSModel model;
	private Holding book1, book2, book3, book4, book5;
	private Holding video1, video2, video3, video4;

	public void setupTestData(LMSModel model) {
		this.model = model;		
		initialiseHoldings();
		addHoldings();
	}

	private void initialiseHoldings() {
		book1 = new Book(1000001, "Introduction to Java Programming");
		book2 = new Book(3400002, "Learning UML");
		book3 = new Book(9876543,
				"Design Patterns - Elements of Reusable Object-Oriented Software");
		book4 = new Book(1001000, "Advanced Java Programming");
		book5 = new Book(3400005, "OO Programming");
		video1 = new Video(2020001, "Java 1", 4);
		video2 = new Video(3000002, "Java 2", 4);
		video3 = new Video(3100003, "UML 1 - Basic", 6);
		video4 = new Video(3200004, "UML 2 - Advanced", 6);
	}

	private void addHoldings() {
		model.addHolding(book1);
		model.addHolding(book2);
		model.addHolding(video1);
		model.addHolding(video2);
		model.addHolding(book3);
		model.addHolding(book4);
		model.addHolding(book5);		
		model.addHolding(video3);
		model.addHolding(video4);
	}
}