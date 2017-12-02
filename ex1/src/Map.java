import java.util.ArrayList;

/**
 * Created by Matanel on 29/11/2017.
 */

public class Map
{
	// Members
	private int m_size;
	private Parser m_parser;
	private String m_algorithm;
	private ArrayList<Cell> m_cellsList;
	private String m_typeString;

	// Constructor
	Map(String inputFile)
	{
		m_parser = new Parser(inputFile);
		m_algorithm = m_parser.getAlgorithm();
		m_size = m_parser.getSize();
		m_typeString = m_parser.getTypeString();

		m_cellsList = new ArrayList<Cell>();
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				m_cellsList.add(new Cell(new Point(i, j), m_typeString.charAt((i * m_size) + j)));
			}
		}
	}

	// Getter
	public Cell getCell(int xVal, int yVal)
	{
		return m_cellsList.get((xVal * m_size) + yVal);
	}

	// Print the map
	public String toString()
	{
		String mapToPrint = new String();
		for (int i = 0; i < m_size; i++)
		{
			for (int j = 0; j < m_size; j++)
			{
				mapToPrint += m_cellsList.get(j + (m_size * i)).toString();
			}
			mapToPrint += '\n';
		}
		return mapToPrint;
	}
}
