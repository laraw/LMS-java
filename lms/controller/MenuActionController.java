package lms.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.view.LMSMain;

public class MenuActionController implements ActionListener {

	private LMSMain main;
	private LMSModel model;

	public MenuActionController(LMSMain main) {
		this.main = main;
		model = main.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Sort Type")) {
			main.getHoldingsView().setsortPref("Type");
			main.getHoldingsView().displayspanels();
		}
		
		if(cmd.equalsIgnoreCase("Sort Code")) {
			main.getHoldingsView().setsortPref("Code");
			main.getHoldingsView().displayspanels();
		}
		
		if(cmd.equalsIgnoreCase("Sort None")) {
			main.getHoldingsView().setsortPref("None");
			main.getHoldingsView().displayspanels();
		}
		

		if (cmd.equalsIgnoreCase("Add Book") || cmd.equalsIgnoreCase("Add Video")) {
			main.openHolding();
		}

		if (cmd.equalsIgnoreCase("Remove Book") || cmd.equalsIgnoreCase("Remove Video"))  {
			Holding holdings[] = model.getAllHoldings();	

			JList<Holding> box = new JList(holdings);
			box.setSelectedIndex(0);
			JLabel removeLabel = new JLabel("Select holding to remove");
			Object[] array = {
					removeLabel,
					box,								
			};


			int result = JOptionPane.showConfirmDialog(main, array, 
					"Please Select Holdings", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {

				int exit = JOptionPane.showConfirmDialog(
						main,
						"Are you sure you want to remove?",
						"Press Yes to Remove, No to cancel",
						JOptionPane.YES_NO_OPTION);
				if(exit == JOptionPane.YES_OPTION) {

					List<Holding> selectedholdings = box.getSelectedValuesList(); 

					for(int i = 0; i < selectedholdings.size(); i++) {
						model.removeHolding(selectedholdings.get(i).getCode());
					}
					
					if (model.getAllHoldings() != null) {
						main.getHoldingsView().displayspanels();
					}
					
					else {
						main.getHoldingsView().removeAll();
						main.getHoldingsView().validate();
						main.getHoldingsView().repaint();
						main.getMenu().disableRemoveHoldings();
						main.getProgMenu().disableRemoveHoldings();
						
					}
					
					
					main.getinfoPanel().updateCollectionInfo();
					main.getinfoPanel().validate();
					main.getinfoPanel().repaint();
				}
			}


		}

		if(cmd.equalsIgnoreCase("Initialise Collection")) {
			main.openCollectionWindow();
		}

		if(cmd.equalsIgnoreCase("Reset Collection")) {

			int exit = JOptionPane.showConfirmDialog(
					main,
					"Are you sure you want to reset collection?",
					"Press Yes to confirm, No to cancel",
					JOptionPane.YES_NO_OPTION);
			if(exit == JOptionPane.YES_OPTION) {
				Holding[] holdings = model.getAllHoldings();

				for(int i = 0; i < holdings.length; i++) {
					model.removeHolding(holdings[i].getCode());
				}
				
				main.getHoldingsView().removeAll();
				main.getHoldingsView().validate();
				main.getHoldingsView().repaint();
				main.getMenu().disableRemoveHoldings();
				main.getProgMenu().disableRemoveHoldings();
				main.getinfoPanel().updateCollectionInfo();
				main.getinfoPanel().validate();
				main.getinfoPanel().repaint();
			}


		}

		if(cmd.equalsIgnoreCase("About")) {
			JTextArea message = new JTextArea("This program was developed "
					+ "as assessment for Programming 2"
					+ ", RMIT University.");
			message.setLineWrap(true);
			message.setWrapStyleWord(true);
			message.setEditable(false);
			message.setBackground(Color.LIGHT_GRAY);
			JOptionPane.showMessageDialog(null, message);
		}

		if(cmd.equalsIgnoreCase("Exit")) {
			int exit = JOptionPane.showConfirmDialog(
					main,
					"Are you sure you want to exit?",
					"Press Yes to Exit, No to stay",
					JOptionPane.YES_NO_OPTION);

			if(exit == JOptionPane.YES_OPTION)
				System.exit(0);    
		}    
	}
}

