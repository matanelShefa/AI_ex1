import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

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
	private PriorityQueue<Cell> m_duplicatesList;
	private int m_cost;

	// Constructor
	AStar(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_maxDepth = (map.getSize() * map.getSize());
		m_root = map.getCell(0, 0);
		m_duplicatesList = new PriorityQueue<Cell>((node1, node2)->
				(heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost()));
		m_solution = "";
		m_cost = 0;
	}

	@Override
	public void search()
	{
		m_duplicatesList.add(m_root);
		//System.out.println(m_root + " ===> ENTERED TO LIST");
		while (!m_duplicatesList.isEmpty())
		{
			Cell node = m_duplicatesList.poll();
			if (node.getType() == Cell.GOAL)
			{
				System.out.println("GOAL FOUND!! HEYDAD!!");
				return;
			}
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);
			for (Cell child : childrenList)
			{
				if ((m_map.isValidMove(node, child)) && (!m_duplicatesList.contains(child)))
				{
					System.out.println(node + " ===> " + child);
					System.out.println(child + " ===> ENTERED TO LIST");
					m_duplicatesList.add(child);
					child.setCost(child.getCost() + node.getCost());
					child.setParent(node);
					child.setHeuristic(heuristic(child));
				}
			}
			m_duplicatesList.remove(node);
			System.out.println(node + " ===> REMOVED FROM LIST");
		}
	}

	// Heuristic on this cell.
	private int heuristic(Cell cell)
	{
		return Math.max(m_size - 1 - cell.getXVal(), m_size - 1 - cell.getYVal());
	}
}
