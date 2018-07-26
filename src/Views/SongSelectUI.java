package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javafx.scene.layout.Pane;
import model.CardReader;
import model.PlayList;
import model.SongCollection;

/*---------------------------------------------------------
 * Class: SongSelectUI
 * Author: Alex Starks
 * 
 * Purpose: This class contains the JPanel that controls the 
 * prompts a user to select songs from the JukeBox object
 * ------------------------------------------------------*/
public class SongSelectUI extends JPanel {

	// ----- instance variables ------
	private CardReader cardReader; // cardReader object
	private PlayList playlist; // playlist object
	private SongCollection songCollection; // song collection object
	private JButton song1Button; // first song
	private JButton song2Button; // second song
	private int HEIGHT; // height of jpanel
	private int WIDTH; // width of jpanel
	private CardReaderUI cardReaderUI; // cardReaderUI object
	private JTable table; // table displayed to user
	private TableModel model; // table model used by jtable
	private JScrollPane pane; // scroll pane that uses table
	private RowSorter<TableModel> sorter; // used by jtable
	// ----- instance variables ------

	/*----------------------------------
	 * Constructor: SongSelectUI
	 * Purpose: To initialize instance variables
	 * and to create the JPanel objects
	 * ---------------------------------*/
	public SongSelectUI(int width, int height, CardReader cR,CardReaderUI cRUI, PlayList playList){
		// set instance variables
		cardReader =cR;
		cardReaderUI = cRUI;
		playlist = playList;
		songCollection = SongCollection.getInstance();
		model = songCollection;
		table = new JTable(model);

		// set jpanel sepcs
		this.HEIGHT = 250; // (int)(height/8);
		this.WIDTH = (int)(width/2);
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		// create sorter with model
		sorter = new TableRowSorter<TableModel>(model);
		// link sorter to table
		table.setRowSorter(sorter);
		// add table to jscrollpane
		pane = new JScrollPane(table);
		// add pane to this panel
		this.add(pane);
		// set size of pane
		pane.setSize(WIDTH,HEIGHT-80);
		
		// ---------------- create button ----------------------
		JButton button = new JButton("Add Highlighted Song");
		ButtonListener buttonListener = new ButtonListener();
		button.addActionListener(buttonListener);
		this.add(button);
		button.setLocation(0,HEIGHT-35);
		button.setSize(WIDTH,35);
		// ---------------- create button ----------------------		  		
	}

	/* 
	 * Button listener class calls song selector object to get and add song to queue 
	 */
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int currentRow = table.getSelectedRow(); // selected row by user
	    	int actualRow = table.convertRowIndexToModel(currentRow); // data selected by user 
			
	    	boolean flag = true;
			if (cardReader.getCurrentUser() == null)
			{
				flag = false;
				JOptionPane.showMessageDialog(null, "There is no user currently logged in.");
			}
			
			if (flag)
			{
				if(songCollection.getSong(actualRow).canPlay()  && cardReader.getCurrentUser().isAllowedToPlaySong())
					playlist.addSong(songCollection.getSong(actualRow),cardReader.getCurrentUser());
				else
					flag = false;
				if(!songCollection.getSong(actualRow).canPlay())
					JOptionPane.showMessageDialog(null, "This song has already been played 3 times today.");
				if(!cardReader.getCurrentUser().isAllowedToPlaySong())
					JOptionPane.showMessageDialog(null, "Current user has already played 3 songs today.");
			}
			if(flag)
			{
				playlist.playNextSong();
				cardReader.getCurrentUser().playedSong();
				cardReaderUI.updateStatus();
			}
		}
	}
}
