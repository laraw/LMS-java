package lms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.*;

import lms.model.facade.LMSModel;

public class InfoPanel extends JPanel {

	
	private JLabel infopanel;
	private LMSMain main;
	private LMSModel model;
	private String collectioninfo;
	private Font font;
	
	public InfoPanel(LMSMain main) {
		this.main = main;
		model = main.getModel();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		infopanel = new JLabel("Collection code: [0] Total Books: [0] Total Videos:"
				+ " [0]");
		add(infopanel);
		
	}

	public void updateCollectionInfo() {
		collectioninfo = new String("Collection code: [" + 
					model.getCollection().getCode() + "]" +
							" Total Books: [" + main.getModel().countBooks() 
							+ "]"
							+ " Total Videos: [" + main.getModel().countVideos() 
							+ "]");
		infopanel.setText(collectioninfo);
		
		
	}
	
	public void resetCollectionInfo() {
		collectioninfo = "Collection code: [0] Total Books: [0] Total Videos:"
				+ " [0]";
		infopanel.setText(collectioninfo);
		
	}

	public LMSModel getModel() {
		return model;
	}

}
