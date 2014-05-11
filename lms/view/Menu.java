package lms.view;

import javax.swing.*;

import java.awt.*;

import lms.controller.CollectionController;
import lms.controller.MenuActionController;
import lms.model.facade.LMSModel;

public class Menu extends JMenuBar {
	
	private JMenuBar menubar;
    private JMenu File, Library, Holdings, Help;
    private JMenuItem addBook, removeBook, addVideo, removeVideo, createCollection
    			, resetCollection, exit, about;   
    
    private LMSMain main;
    private LMSModel model;
    
	public Menu(LMSMain main) {
		this.main = main;
		initComponents();
		addComponents();
		main.setJMenuBar(menubar);
		model = main.getModel();
		
	}
	
	private void initComponents() {
		
		// Instantiate our menu items
		menubar = new JMenuBar();
		File = new JMenu("File");
		Library = new JMenu("Library");
		Holdings = new JMenu("Holdings");
		Help = new JMenu("Help");
		
		// add book menu item will allow the user to add the book and
		// calls the action listener method 
		
		addBook =  new JMenuItem("Add Book");
		addBook.addActionListener(new MenuActionController(main));
		addBook.setEnabled(false);
		
		removeBook = new JMenuItem ("Remove Book");
		removeBook.addActionListener(new MenuActionController(main));
		removeBook.setEnabled(false);
		
		addVideo = new JMenuItem("Add Video");
		addVideo.addActionListener(new MenuActionController(main));
		addVideo.setEnabled(false);
		
		removeVideo = new JMenuItem("Remove Video");
		removeVideo.addActionListener(new MenuActionController(main));
		removeVideo.setEnabled(false);
		
		// 
		createCollection = new JMenuItem("Initialise Collection");		
		createCollection.addActionListener(new MenuActionController(main));
		
		resetCollection = new JMenuItem("Reset Collection");
		resetCollection.addActionListener(new MenuActionController(main));
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuActionController(main));
		about = new JMenuItem("About");
		about.addActionListener(new MenuActionController(main));
		
		// instantiate the menus & add the menu items to each menu
		
		File.add(exit);
		Library.add(createCollection);
		Library.add(resetCollection);
		Holdings.add(addBook);
		Holdings.add(removeBook);
		Holdings.add(addVideo);
		Holdings.add(removeVideo);
		Help.add(about);
		
	}
	
	private void addComponents() {
		
		// add the menus to the menu bar
		menubar.add(File);
		menubar.add(Library);
		menubar.add(Holdings);
		menubar.add(Help);
		
	}
	
	public void enableMenuItems() {
		addBook.setEnabled(true);
		removeBook.setEnabled(true);
		addVideo.setEnabled(true);
		removeVideo.setEnabled(true);
	}
	
	public void disableRemoveHoldings() {
		removeBook.setEnabled(false);
		removeVideo.setEnabled(false);
	}
	
	public void enableRemoveHoldings() {
		removeBook.setEnabled(true);
		removeVideo.setEnabled(true);
	}
	
	public LMSModel getModel() {
		return model;
	}
}
