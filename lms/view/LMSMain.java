package lms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lms.model.facade.LMSModel;

public class LMSMain extends JFrame {
	
	private LMSModel model;
	private InfoPanel info;
	private ToolBar progmenu;
	private Menu menu;
	private LibraryGrid holdings;
	private AddHolding addholding;
	
	
	public LMSMain(LMSModel model) throws HeadlessException {
		this.model = model;
		setLayout(new BorderLayout());
		setTitle("OUA CPT221 - SP2014 - Lara Wilkinson s3342496");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(10,10);		
		addComponents();
		
	}
	
	public LMSModel getModel() {
		return model;
	}
	
	public void addComponents() {

		// add all the components of the view to the main frame
		
		info = new InfoPanel(this);
		add(info, BorderLayout.SOUTH);
		progmenu = new ToolBar(this);
		add(progmenu, BorderLayout.NORTH);
		holdings = new LibraryGrid(this);
		add(holdings, BorderLayout.CENTER);
		
		// add the menu bar
		menu = new Menu(this);
		
	}
	
	public InfoPanel getinfoPanel() {
		return info;
	}
	
	public ToolBar getProgMenu() {
		return progmenu;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public LibraryGrid getHoldingsView() {
		return holdings;
	}
	
	public AddHolding getAddHoldingsView() {
		return addholding;
	}
	
	public void openCollectionWindow() {
		InitialiseCollection window = new InitialiseCollection(this);
	}
	
	public void openHolding() {
		AddHolding holding = new AddHolding(this);		
		holding.setVisible(true);
	}
	
	 protected void processWindowEvent(WindowEvent e) {
	        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	            int exit = JOptionPane.showConfirmDialog(this, 
	                    "Are you sure you want to exit?", 
	                    "Yes to Exit, No to stay", 
	                    JOptionPane.YES_NO_OPTION);
	            if(exit == JOptionPane.YES_OPTION)
	                System.exit(0);
	        }
	        else
	                super.processWindowEvent(e);
	    }


	

}
