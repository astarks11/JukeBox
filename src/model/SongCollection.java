package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * Class: SongCollection
 * Author: Matthew Drake
 * 
 * This class hold all of the songs for the program. If you
 * actually look at this class, you'll find a bunch of extra
 * classes, we put them in this class in case it was necessary
 * to list them out. So far, it is not necessary so I have commented
 * out all of those methods, but they may be useful for iteration
 * 2 so they are still here
 */
public class SongCollection implements Serializable, TableModel
{
	private List<Song> songs;
	private static SongCollection songCollection;
	private SongCollection()
	{
		songs = new ArrayList<Song>();
		addSongs();
	}
	/*
	 * This method hard codes all of the songs that can be used. 
	 * It simply creates song objects and adds them to the ArrayList of songs
	 */
	private void addSongs()
	{
		songs.add(new Song("./songfiles/flute.aif", 6, "Sun Microsystems", "Flute"));
		songs.add(new Song("./songfiles/LopingSting.mp3", 5, "Kevin MacLeod", "Loping Sting"));
		songs.add(new Song("./songfiles/DanseMacabreViolinHook.mp3", 34, "Kevin MacLeod", "Danse Macabre"));
		songs.add(new Song("./songfiles/DeterminedTumbao.mp3", 20, "FreePlay Music", "Determined Tumbao"));
		songs.add(new Song("./songfiles/spacemusic.au", 6, "Unknown", "Space Music"));
		songs.add(new Song("./songfiles/SwingCheese.mp3", 15, "FreePlay Music", "Swing Cheese"));
		songs.add(new Song("./songfiles/tada.wav", 2, "Microsoft", "Tada"));
		songs.add(new Song("./songfiles/TheCurtainRises.mp3", 28, "Kevin MacLeod", "The Curtain Rises"));
		songs.add(new Song("./songfiles/UntameableFire.mp3", 282, "Pierre Langer", "Untamable Fire"));
	}
	/*
	 * Given an index from which to get the song, this method returns the song object that is
	 * in the array list at the index that is given as a parameter of this class. This class may return
	 * an error if you attempt to fetch a value that is negative or at an index that is not used in the
	 * arraylist.
	 */
	public Song getSong(int index)
	{
		return songs.get(index);
	}
	 public static SongCollection getInstance()
	 {
		 if (songCollection == null) {
			 songCollection = new SongCollection();
			 }
			 return songCollection;
	 }
	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return songs.size();
	}
	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public String getColumnName(int columnIndex)
	{
		// TODO Auto-generated method stub
		if(columnIndex == 0)return "Artist";
		else if(columnIndex == 1)return "Song Title";
		else if(columnIndex == 2)return "Seconds";
		return null;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		if(columnIndex == 0)return String.class;
		else if(columnIndex == 1)return String.class;
		else if(columnIndex == 2)return Integer.class;
		return null;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Song temp = songs.get(rowIndex);
		
		if(columnIndex == 0)return temp.getArtistName();
		else if(columnIndex == 1) return temp.getSongTitle();
		else if(columnIndex == 2) return temp.getLength();
		
		return null;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTableModelListener(TableModelListener l)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeTableModelListener(TableModelListener l)
	{
		// TODO Auto-generated method stub
		
	}
	
}
