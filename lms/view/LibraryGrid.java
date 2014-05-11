package lms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import lms.controller.GridMouseListener;
import lms.model.*;
import lms.model.facade.LMSModel;

public class LibraryGrid extends JPanel {
	
	private LMSMain main;
	private LMSModel model;
	private final Border bookBorder = BorderFactory.createLineBorder(Color.blue, 3);
    private final Border videoBorder = BorderFactory.createLineBorder(Color.red, 3);
    private List<Holding> holdingslist = new ArrayList<Holding>();
    private GridLayout gridlayout;
    private String sortPref;
    private HashMap<JPanel,Holding> map = new HashMap<JPanel,Holding>(); 
     
    
public LibraryGrid(LMSMain main) {
		this.main = main;		
		model = main.getModel();
		gridlayout = new GridLayout(0,4);
		setLayout(gridlayout);
		setBorder(new LineBorder(Color.BLACK, 1));
		this.setAlignmentX(FlowLayout.LEFT);;
		setVisible(true);	
		sortPref = "Type";
		
	}
	
	
	public LMSMain getmain() {
		return main;
	}

	
	public void displayspanels() {
		sortPanels();
		removeAll();
		
		int numpanels = holdingslist.size();
		
		
		if(numpanels >= 4) {
			gridlayout.setColumns(4);
		}
		
		else
			gridlayout.setColumns(numpanels);
		
		for(int i=0;i< holdingslist.size();i++) {						
			JPanel panel = new JPanel();
			map.put(panel,holdingslist.get(i));
			JScrollPane scrollPane = new JScrollPane();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.setBackground(Color.LIGHT_GRAY);
			scrollPane.add(panel);
			
			scrollPane.getViewport().add(panel);
			add(scrollPane);
			
			JLabel codelabel = 
					new JLabel("Holding ID: " + holdingslist.get(i).getCode());
			JLabel titleLabel= 
					new JLabel("Holding Title: " + holdingslist.get(i).getTitle());
			JLabel newLoanLabel =
					new JLabel("Standard Loan Fee: " 
								+	holdingslist.get(i).getDefaultLoanFee());
			JLabel newLoanPeriod = 
					new JLabel("Loan Period: " + holdingslist.get(i).getMaxLoanPeriod());
			
			if(holdingslist.get(i) instanceof Book) {
				panel.setBorder(bookBorder);
			}
			else {
				panel.setBorder(videoBorder);
			}
			panel.add(codelabel);
			panel.add(titleLabel);
			panel.add(newLoanLabel);
			panel.add(newLoanPeriod);


			panel.addMouseListener(new GridMouseListener(this));
			
		
			
			validate();
			repaint();
		}
		
	}
	
	
	public String getsortPref() {
		return sortPref;
	}
	
	public void setsortPref(String sortPref) {
		this.sortPref = sortPref;
	}
	
	public void sortPanels() {
		
		
		holdingslist = new ArrayList<Holding>();
		if(model.getAllHoldings()!=null) {
			if(sortPref.equalsIgnoreCase("Code")) {
				holdingslist = Arrays.asList(model.getAllHoldings());
			}
			else if(sortPref.equalsIgnoreCase("None")) {
				holdingslist = Arrays.asList(model.getAllHoldings());
			}
			else if(sortPref.equalsIgnoreCase("Type")) {
				ArrayList<Holding> holdings = 
						new ArrayList<>(Arrays.asList(model.getAllHoldings()));
				
				for(int i= 0; i< holdings.size(); i++) {
					if(holdings.get(i) instanceof Book) {
						holdingslist.add(holdings.get(i));
					}
				}
				for(int i=0; i<holdings.size();i++) {
					if(holdings.get(i) instanceof Video) {
						holdingslist.add(holdings.get(i));
					}
				}
			}
			
		}
		
		else {
			removeAll();
			validate();
			repaint();
			
		}
		
	}
	
	public HashMap<JPanel,Holding> getMap() {
		return map;
	}
	
	public LMSModel getModel() {
		return model;
	}
	
}