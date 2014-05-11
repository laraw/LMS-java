package lms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.*;

import lms.controller.CollectionController;
import lms.model.facade.LMSModel;
public class InitialiseCollection extends JFrame {

	private JPanel panel1,panel2,panel3;
	private JTextField collectionCode, collectionName;
	private JLabel codeLabel, nameLabel;
	private JButton add;
	private JButton cancel;
	private LMSMain main;
	private LMSModel model;
	
	public InitialiseCollection(LMSMain main) throws HeadlessException {
		this.main = main;
		model = main.getModel();
		setVisible(true);
		setSize(300,200);
		setTitle("Initialise Collection");
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		addCollectionFields();
	}
	
	private void addCollectionFields() {
		
		panel1 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel(new BorderLayout());
		collectionCode = new JTextField(15);
		collectionName = new JTextField(15);
		codeLabel = new JLabel("Collection code: ");
		nameLabel = new JLabel("Collection name: ");
		add = new JButton("OK");
		cancel = new JButton("Cancel");
		panel1.add(codeLabel, BorderLayout.WEST);
		panel1.add(collectionCode, BorderLayout.EAST);
		panel2.add(nameLabel, BorderLayout.WEST);
		panel2.add(collectionName, BorderLayout.EAST);
		panel3.add(add, BorderLayout.WEST);
		panel3.add(cancel, BorderLayout.EAST);
		add(panel1, FlowLayout.LEFT);
		add(panel2);
		add(panel3);
		
		add.addActionListener(new CollectionController(this));
		cancel.addActionListener(new CollectionController(this));
	}

	public String getCollectionCode() {
		return collectionCode.getText();
	}
	
	public String getName() {
		return collectionName.getText();
	}
	
	public LMSMain getmainview() {
		return main;
	}
	
	public LMSModel getModel() {
		return model;
	}
}