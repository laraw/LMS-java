package lms.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.view.LibraryGrid;



public class GridMouseListener implements MouseListener {

	private LibraryGrid view;
	private LMSModel model;
	private HashMap<JPanel, Holding> map;
	
	public GridMouseListener(LibraryGrid view) {
		this.view = view;
		model = view.getModel();
		map = view.getMap();
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		JPanel source = (JPanel) e.getSource();
		if(e.getButton() == MouseEvent.BUTTON1) {
			int exit = JOptionPane.showConfirmDialog(
					view,
					"Are you sure you want to remove the holding?",
					"Press Yes to Confirm, No to Cancel",
					JOptionPane.YES_NO_OPTION);
			if(exit == JOptionPane.YES_OPTION) {
				model.removeHolding(map.get(source).getCode());
				if(model.getAllHoldings() != null) {
					view.displayspanels();
				}
				else {
					view.getmain().getMenu().disableRemoveHoldings();
					view.getmain().getProgMenu().disableRemoveHoldings();
					view.removeAll();
					view.validate();
					view.repaint();
				}
			}  
			
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
