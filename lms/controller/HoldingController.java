package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import lms.model.*;
import lms.model.exception.MultipleBorrowingException;
import lms.model.facade.LMSModel;
import lms.view.AddHolding;
import lms.view.InitialiseCollection;
import lms.view.util.Tester;

public class HoldingController implements ActionListener {

	private AddHolding view;
	private LMSModel model;

	public HoldingController(AddHolding view) {
		this.view= view;
		model = view.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();


		if (cmd.equalsIgnoreCase("addcancel")) {
			int exit = JOptionPane.showConfirmDialog(
					view,
					"Are you sure you want to cancel?",
					"Press Yes to Exit, No to stay",
					JOptionPane.YES_NO_OPTION);
			if(exit == JOptionPane.YES_OPTION)
				view.setVisible(false);

			view.getmainview().repaint();

		}




		if (cmd.equalsIgnoreCase("OK")) {

			String code = view.getCodeInput();
			String title = view.getNameInput();
			boolean valid = true;
			JOptionPane errordialogue = new JOptionPane();

			try {

				if(code.length() != 7) {
					throw new Exception("Code must be 7 characters long");
				}

				if(title.length() < 3) {
					throw new Exception("Title must be at least 3 characters");
				}

				int codenum = Integer.parseInt(view.getCodeInput());				

				if(model.getHolding(codenum) != null) {
					throw new MultipleBorrowingException("Holding already exists");
				}

				if (view.getVideoChoice().isSelected()) {
					int defaultFee = 0;
					if (view.getFee6Choice().isSelected()){
						defaultFee = 6;
					}
					else {
						defaultFee = 4;
					}
					model.addHolding(new Video(codenum,
							title,defaultFee));
				}
				else {
					model.addHolding(new Book(codenum, 
							title));
				}
				
				
				view.getmainview().getHoldingsView().displayspanels();
				view.getmainview().getProgMenu().enableRemoveHoldings();
				view.getmainview().getMenu().enableRemoveHoldings();
				view.setVisible(false);
				view.getmainview().getinfoPanel().updateCollectionInfo();
				view.getmainview().getinfoPanel().validate();
				view.getmainview().getinfoPanel().repaint();

			}
			catch (NumberFormatException ex) {	
				errordialogue.showMessageDialog(view, "Code must be a number");
			}		
			catch (Exception ex) {
				errordialogue.showMessageDialog(view, ex.getMessage());
			}
		}		

		if (cmd.equalsIgnoreCase("video")) {
			view.displayFeePanel(true);
			view.repaint();
		}

		if (cmd.equalsIgnoreCase("book")) {
			view.displayFeePanel(false);
			view.repaint();
		}
	}
}
