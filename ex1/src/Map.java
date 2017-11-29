import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Map
{
	// Members
	private int m_size;
	private ArrayList<Cell> cellsList;

	// Constructor
	Map(int size)
	{
		m_size = size;
		cellsList = new ArrayList<Cell>();
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				cellsList.add(new Cell(new Point(i, j)));
			}
		}
	}

	// Print the map
	public String toString()
	{
		String mapToPrint = new String();
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				mapToPrint += cellsList.get(j + (m_size * i)).toString();
			}
			mapToPrint += '\n';
		}
		return mapToPrint;
	}

}
