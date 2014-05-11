package lms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lms.controller.MenuActionController;
import lms.model.facade.LMSModel;


public class ToolBar extends JPanel {

	private LMSMain main;
	private LMSModel model;
	private JPanel images, order;
	private JRadioButton nonebutton, codebutton, typebutton;
	private ButtonGroup buttons;
	private JLabel buttonlabel;
	private JButton addbook, deletebook, addcollection, addfilm, deletefilm;


	public ToolBar(LMSMain main) {
		this.main = main;
		model = main.getModel();

		// set the layout of the program menu panel to be border layout
		// so that the components can be placed at either side of the panel

		setLayout(new BorderLayout());
		setSize(200,200);
		addComponents();



	}

	private void addComponents() {
		// set up the images panel

		images = new JPanel(new FlowLayout());		
		add(images, BorderLayout.WEST);

		// creating icon buttons and adding it to the images panel


		addbook = new JButton(new ImageIcon("src/book_add.png"));
		addbook.setActionCommand("Add Book");
		addbook.setEnabled(false);
		deletebook = new JButton(new ImageIcon("src/book_delete.png"));
		deletebook.setActionCommand("Remove Book");
		deletebook.setEnabled(false);
		addcollection = new JButton(new ImageIcon("src/collection.png"));
		addcollection.setActionCommand("Initialise Collection");
		addfilm = new JButton(new ImageIcon("src/film_add.png"));
		addfilm.setActionCommand("Add Video");
		addfilm.setEnabled(false);
		deletefilm = new JButton(new ImageIcon("src/film_delete.png"));
		deletefilm.setActionCommand("Remove Video");
		deletefilm.setEnabled(false);

		images.add(addbook);
		images.add(deletebook);
		images.add(addcollection);
		images.add(addfilm);
		images.add(deletefilm);


		// set up the radio button panel

		order = new JPanel(new FlowLayout());
		add(order, BorderLayout.EAST);


		// adding the radio button group to the radio button pannel

		buttons = new ButtonGroup();
		nonebutton = new JRadioButton("None");
		codebutton = new JRadioButton("Code");
		typebutton = new JRadioButton("Type");
		buttons.add(nonebutton);
		nonebutton.setActionCommand("Sort None");
		nonebutton.addActionListener(new MenuActionController(main));
		buttons.add(codebutton);
		codebutton.setActionCommand("Sort Code");
		codebutton.addActionListener(new MenuActionController(main));
		buttons.add(typebutton);
		typebutton.setActionCommand("Sort Type");
		typebutton.addActionListener(new MenuActionController(main));
		buttons.setSelected(nonebutton.getModel(), true);
		

		JLabel buttonlabel = new JLabel("SORT ORDER");

		order.add(buttonlabel);		
		order.add(nonebutton);
		order.add(codebutton);
		order.add(typebutton);

		addcollection.addActionListener(new MenuActionController(main));
		addbook.addActionListener(new MenuActionController(main));
		addfilm.addActionListener(new MenuActionController(main));
		deletefilm.addActionListener(new MenuActionController(main));
		deletebook.addActionListener(new MenuActionController(main));
	}

	public void enableMenuItems() {
		addbook.setEnabled(true);
		deletebook.setEnabled(true);
		addfilm.setEnabled(true);
		deletefilm.setEnabled(true);
	}
	
	public void disableRemoveHoldings() {
		deletefilm.setEnabled(false);
		deletebook.setEnabled(false);
		nonebutton.setEnabled(false);
		typebutton.setEnabled(false);
		codebutton.setEnabled(false);
	}
	
	public void enableRemoveHoldings() {
		deletefilm.setEnabled(true);
		deletebook.setEnabled(true);
		nonebutton.setEnabled(true);
		typebutton.setEnabled(true);
		codebutton.setEnabled(true);
	}
	
	public LMSModel getModel() {
		return model;
	}


}
