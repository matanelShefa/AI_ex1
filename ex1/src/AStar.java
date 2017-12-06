import java.util.HashSet;

/**
 * Created by Matanel on 06/12/2017.
 */
public class AStar extends Searcher
{
	// Members
	private int m_size;
	private int m_maxDepth;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private HashSet<Cell> m_seenList;
	private int m_cost;

	// Constructor
	AStar(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_maxDepth = (map.getSize() * map.getSize());
		m_root = map.getCell(0, 0);
		m_seenList = new HashSet<>();
		m_solution = "";
		m_cost = 0;
	}

	@Override
	public void search()
	{

	}

	// Heuristic on this cell.
	private double heuristic(Cell cell)
	{
		return Math.sqrt((m_size - cell.getXVal())+(m_size - cell.getYVal()));
	}
}
