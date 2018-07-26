package controller;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Views.CardReaderUI;
import Views.SongListUI;
import Views.SongSelectUI;
import model.CardReader;
import model.PlayList;
import songPlayerDemo.Play1Song;
/*-------------------------------------------------
 * Class: JukeBoxGUI
 * Author: Alex Starks
 * 
 * Purpose: This class is the JFrame that the user will interact with.
 */
public class JukeBoxGUI extends JFrame {

	// instance variables
	private CardReader cardReader; // cardReader object
	private final int WIDTH = 460; // interface width
	private final int HEIGHT = 750; // interface height
	private PlayList playlist; // playlist object for UI's
	private SongListUI songListUI; 
	private boolean loadSave;
	private CardReaderUI cardReaderUI;
	private SongSelectUI songSelectUI;

	/*----------------------------------------------------
	 * Constructor: JukeBoxGUI()
	 * Purpose: This constructor is responsible for setting instance variables
	 * and creating the JFrame
	 * --------------------------------------------------*/
	public JukeBoxGUI() {
		// set close, height, location, title

		int userInput =    
				JOptionPane.showConfirmDialog(null, "Start with previous state?\nNo means begin with default jukebox");
		if (userInput == JOptionPane.YES_OPTION)
			loadSave = true;
		else 
			loadSave = false;
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(0, 0);
		this.setTitle("JukeBox");	  

		// inititalize instance variables
		if(loadSave)
		{
			FileInputStream f_in;
			try
			{
				f_in = new 
						FileInputStream("cardReader.data");
				// Read object using ObjectInputStream
				ObjectInputStream obj_in = 
						new ObjectInputStream (f_in);

				// Read an object
				cardReader = (CardReader) obj_in.readObject();

				obj_in.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			cardReader = new CardReader();
		
		songListUI = new SongListUI(WIDTH, HEIGHT);
		this.add(songListUI);
		if(loadSave)
		{
			FileInputStream f_in;
			try
			{
				f_in = new 
						FileInputStream("playList.data");
				// Read object using ObjectInputStream
				ObjectInputStream obj_in = 
						new ObjectInputStream (f_in);

				// Read an object
				playlist = (PlayList) obj_in.readObject();

				obj_in.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			playlist = new PlayList(songListUI);
		// -------------------- initialize interface ------------------------------
		cardReaderUI = new CardReaderUI(WIDTH,HEIGHT,cardReader);
		this.add(cardReaderUI);

		songListUI.update(null, playlist.getCurrentPlaylist());
		playlist.start();
		
		playlist.addObserver(songListUI);
		songSelectUI = new SongSelectUI(WIDTH,HEIGHT,cardReader,cardReaderUI,playlist);
		this.add(songSelectUI);

		// set weird locations yo
		cardReaderUI.setLocation((int)(WIDTH/5), (int)(HEIGHT/17));
		songSelectUI.setLocation((int)(WIDTH/5),(int)(HEIGHT/5) + 20); 
		songListUI.setLocation((int)(WIDTH/5), (int)(HEIGHT/2 + 80));

		ListenForWindowClose windowListener = new ListenForWindowClose();
		this.addWindowListener(windowListener);
		// -------------------- initialize interface ------------------------------

	}

	private class ListenForWindowClose extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO 2: Ask user to save the modified list or to not write a persistent object
			int userInput = JOptionPane.showConfirmDialog(null, "Would you like to save your jukebox?");
			cardReader.setCurrentUser(null);

			if (userInput == JOptionPane.YES_OPTION)
			{
				// Write to disk with FileOutputStream
				FileOutputStream f_out;
				ObjectOutputStream obj_out;
				try
				{
					f_out = new FileOutputStream("playList.data");
					// Write object with ObjectOutputStream
					obj_out = new ObjectOutputStream (f_out);

					// Write object out to disk
					obj_out.writeObject (playlist);
					obj_out.close();
					
					f_out = new FileOutputStream("cardReader.data");
					// Write object with ObjectOutputStream
					obj_out = new ObjectOutputStream (f_out);

					// Write object out to disk
					obj_out.writeObject (cardReader);
					obj_out.close();
					
				}
				catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}

		}
	}
} // JukeBoxGUI