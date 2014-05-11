package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.view.LMSMain;

public class GridController implements ActionListener {
	
	private LMSMain main;
	
	public GridController(LMSMain main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		

}
}