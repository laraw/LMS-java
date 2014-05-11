package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import lms.model.LibraryCollection;
import lms.model.facade.LMSModel;
import lms.view.InitialiseCollection;
import lms.view.util.Tester;

public class CollectionController implements ActionListener {

	private InitialiseCollection initcoll;
	private LMSModel model;
	
	public CollectionController(InitialiseCollection initcoll) {
		this.initcoll = initcoll;
		model = initcoll.getModel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		JOptionPane errordialogue = new JOptionPane();
		if (cmd.equalsIgnoreCase("cancel")) {
			int exit = JOptionPane.showConfirmDialog(
                    initcoll,
                    "Are you sure you want to cancel?",
                    "Press Yes to Exit, No to stay",
                    JOptionPane.YES_NO_OPTION);
            if(exit == JOptionPane.YES_OPTION)
                initcoll.setVisible(false);
            
            initcoll.getmainview().repaint();

		}
		
		if (cmd.equalsIgnoreCase("OK")) {
			try {
				if(model.getCollection() != null) {
					throw new Exception("Collection has been initialised");
				}
				String code = initcoll.getCollectionCode();
				String name = initcoll.getName();
				model.addCollection(new LibraryCollection(code, name));
				Tester test = new Tester();
				test.setupTestData(model);
				initcoll.getmainview().getProgMenu().enableMenuItems();
				initcoll.getmainview().getMenu().enableMenuItems();
				initcoll.setVisible(false);
				initcoll.getmainview().getinfoPanel().updateCollectionInfo();
				initcoll.getmainview().getinfoPanel().repaint();
				initcoll.getmainview().getHoldingsView().displayspanels();
				initcoll.getmainview().getHoldingsView().validate();
				initcoll.getmainview().getHoldingsView().repaint();
			}
			catch (Exception ex) {
				errordialogue.showMessageDialog(initcoll,ex.getMessage());
			}
			
		}
		

	}

}
