package lms.view.main;

import lms.model.*;
import lms.model.facade.LMSFacade;
import lms.model.facade.LMSModel;
import lms.view.LMSMain;
import lms.view.util.Tester;

public class LMSDriver {
	

	public static void main(String[] args) {

		LMSModel model = new LMSFacade();
		LMSMain main = new LMSMain(model);
		
		main.setSize(1200,800);
		main.setVisible(true);
		
		
	}

}
