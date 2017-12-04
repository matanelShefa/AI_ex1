import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Matanel on 03/12/2017.
 */
public class IDS
{
	// Finals
	private static final char GOAL = 'G';

	// Members
	private int m_size;
	private int m_maxDepth;
	private int m_pathCost;
	private Cell m_root;
	private String m_path;
	private Map m_map;
	private HashSet<Cell> m_seenList;
	private String m_solution;


	// Constructor
	IDS(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_maxDepth = (m_size * m_size);
		m_root = map.getCell(0, 0);
		m_seenList = new HashSet<>();
	}

	Cell idsSearch()
	{
		Cell goal = null;
		for (int currentDepth = 0; currentDepth < m_maxDepth; currentDepth++)
		{
			System.out.println("========== Depth: " + currentDepth + " ==========");
			goal = ids(m_root, currentDepth);
			if (goal != null)
			{
				return goal;
			}
		}
		return null;
	}

	Cell ids(Cell node, int depth)
	{
		String currentSolution;
		Cell goal = null;
		if (depth == 0 && node.getType() == GOAL)
		{
			return node;
		}
		if (depth > 0)
		{
			ArrayList<Cell> childrenList = node.getChildrenList();
			for (Cell child : childrenList)
			{
				if ((m_map.isValidMove(node, child)) && (!m_seenList.contains(child)))
				{
					// TODO - REMOVE
					System.out.println("Depth: " + depth + ", " + node + " ===> " + child);
					m_seenList.add(child);
					currentSolution = m_solution;
					m_solution += " " + child;
					goal = ids(child, depth - 1);
					m_seenList.remove(child);
					if (goal != null)
					{
						System.out.println(m_solution);
						return goal;
					}
					m_solution = currentSolution;
				}
			}
		}
		return null;
	}
}
