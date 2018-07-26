package controller;

import model.CardReader;
import model.UserCollection;

/*--------------------------------------------------------------
 * Class: JukeBox
 * Author: Alex Starks
 * 
 * Purpose: This class coordinates activities for the JukeBox project. 
 * This class contains the main method that creates the JukeBox object
 * to start the JukeBox item.
 * ------------------------------------------------------------*/
public class JukeBox {
	/*----------------------------------------------------
	 * Method: main()
	 * Purpose: This method initializes the JukeBox object
	 * and starts the GUI interface.
	 * --------------------------------------------------*/
	  public static void main(String[] args) 
	  {
		  // create JukeBox objects
		  JukeBox jukeBox = new JukeBox();
	  }
  
  /*------------------------------------------------
   * Constructor: Jukebox()
   * Purpose: This constructor initializes the JukeBox 
   * instance variables.
   * ----------------------------------------------*/
  public JukeBox() {
	  
	  // create GUI object
	  JukeBoxGUI gui = new JukeBoxGUI();
	  // set visible
	  gui.setVisible(true);
  } // constructor
  
  
  
  
  

  
  
} // JukeBox