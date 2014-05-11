/* 
 Author: Lara Wilkinson s3342496
 Email: s3342496@student.rmit.edu.au
 Subject: Assignment 2 for Programming 2, RMIT University
  
 The add holding view class provides an interface for adding both books
 and videos to the library collection. 
 
 */

package lms.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.*;

import lms.controller.CollectionController;
import lms.controller.HoldingController;
import lms.model.facade.LMSModel;

public class AddHolding extends JFrame {
	
	/* The add holding view class has a number of GUI elements as properties
	 the view is a frame consisting of panels for the label & text field 
	 combinations. */ 
	
	private JPanel codepanel,titlepanel,loanpanel,buttonspanel,choicepanel;
	private JTextField code, title;
	private JLabel codeLabel, titleLabel, feeLabel, choiceLabel;
	private JButton add;
	private JButton cancel;
	private LMSMain main;
	private JRadioButton bookchoice, videochoice, fee4, fee6;
	private LMSModel model;
	
	/* The constructor takes the main view as a parameter so that it the
	 * main view has access to this view */
	public AddHolding(LMSMain main) throws HeadlessException {
		this.main = main;
		model = main.getModel();
		setVisible(false);;
		setSize(448,200);
		setTitle("Add Holding");
		setResizable(false);
		setLayout(new CardLayout());
		addFields(this.getContentPane());
	}
	
	// The add fields method instantiates and adds all the view components  
	
	private void addFields(Container pane) {
		
		// The panels contain the labels & fields for the view 
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		codepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loanpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loanpanel.setVisible(false);
		buttonspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		choicepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		// These fields provide input for the code & title of the holding
		code = new JTextField(15);
		title = new JTextField(15);
				
		// These elements provide prompts for the user to know what the field is 
		// for
		codeLabel = new JLabel("Holding code: ");
		titleLabel = new JLabel("Holding name: ");
		feeLabel = new JLabel("Standard Loan Fee: ");
		choiceLabel = new JLabel("Book or Video?: ");
		
		
		add = new JButton("OK");
		cancel = new JButton("Cancel");
		cancel.setActionCommand("addcancel");
		// The user is given a choice between book or video
		ButtonGroup holdingbg = new ButtonGroup();
		bookchoice = new JRadioButton("Book");
		videochoice = new JRadioButton("Video");
		videochoice.setActionCommand("video");
		bookchoice.setActionCommand("book");
		holdingbg.add(bookchoice);
		holdingbg.add(videochoice);
		
		// These action listeners will manipulate fields appearing or not
		// appearing on the screen 
		videochoice.addActionListener(new HoldingController(this));
		bookchoice.addActionListener(new HoldingController(this));
		
		// We enforce a choice between 4 and 6 dollars for the video standard
		// loan fee
		
		ButtonGroup feebg = new ButtonGroup();
		fee4 = new JRadioButton("$4.00");
		fee6 = new JRadioButton("$6.00");
		feebg.add(fee4);
		feebg.add(fee6);
		
		// Adding all the elements to the panels
		codepanel.add(codeLabel);
		codepanel.add(code);
		titlepanel.add(titleLabel);
		titlepanel.add(title);
		loanpanel.add(feeLabel);
		loanpanel.add(fee4);
		loanpanel.add(fee6);
		buttonspanel.add(add);
		buttonspanel.add(cancel);		
		choicepanel.add(choiceLabel);
		choicepanel.add(bookchoice);
		choicepanel.add(videochoice);
		
			
		// add the components to the main panel
		choicepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		codepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		titlepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		loanpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonspanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Add the panels to the main view
		
		add(choicepanel);
		add(codepanel);
		add(titlepanel);		
		add(loanpanel);
		add(buttonspanel);
		
		// These button listeners will determine the next step based on the
		// users input
		
		add.addActionListener(new HoldingController(this));
		cancel.addActionListener(new HoldingController(this));
	}

	// Allows the controller to access the users input for code
	public String getCodeInput() {
		return code.getText();
	}
	
	// Allows the controller to access the users input for name
	public String getNameInput() {
		return title.getText();
	}
	
	// This fee panel is toggled on or off based on the users selection of 
	// holding type
	public void displayFeePanel(Boolean flag) {
		loanpanel.setVisible(flag);
	}
	
	//Provides an accessor for the main view 
	public LMSMain getmainview() {
		return main;
	}
	
	// Allows the controller to access the users input for video or book choice
	
	public JRadioButton getVideoChoice() {
		return videochoice;
	}
	public JRadioButton getBookChoice() {
		return bookchoice;
	}
	
	//Allows the controller to access the users input for fee choice
	
	public JRadioButton getFee6Choice() {
		return fee6;
	}
	
	public LMSModel getModel() {
		return model;
	}
	
}
