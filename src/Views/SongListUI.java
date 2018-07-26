package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SongListUI extends JPanel implements Observer, Serializable
{
	private List<String> listOfSongs;
	private int width;
	private int height; 
	private int xLocation; // xLocation
	private int yLocation; // yLocation
	private JTextArea textArea;

	public SongListUI(int width, int height)
	{
		this.width = (int)width/2;
		this.height = 250;
		this.xLocation = 0;
		this.yLocation = 0;
		this.setSize(this.width,this.height);
		this.setBackground(Color.WHITE);
		this.setLocation(xLocation, yLocation);

		textArea = new JTextArea();
		textArea.setVisible(true);
		textArea.setLocation(xLocation, yLocation);
		textArea.setSize(this.width, this.height);
		textArea.setText("No Songs Currently in Playlist");
		textArea.setEditable(false);
		this.add(textArea);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg)
	{
		listOfSongs = (List<String>) arg;
		textArea.setText(null);
		if(listOfSongs.isEmpty())textArea.setText("No Songs Currently in Playlist");
		else
		{
			String tempStr = "";
			for (String str : listOfSongs) {
				tempStr = tempStr + str + "\n";
			}
			textArea.setText(tempStr);
		}
	}
	
	
}
