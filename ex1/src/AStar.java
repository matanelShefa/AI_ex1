import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/************************************************************************************
 * Created by Matanel on 06/12/2017.												*
 * This class is an implement of the A* algorithm for an Informal search in graph.	*
 * The algorithm calculates the next step to me the most efficient, summing the		*
 * real cost with a heuristic prediction.											*
 ***********************************************************************************/
public class AStar extends Searcher
{
	// Finals
	private static final int MAX_COST = 10;

	// Members
	private String NO_PATH = "no path";
	private int m_size;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private PriorityQueue<Cell> m_priorityQueue;
	private int m_cost;

	// Constructor
	AStar(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_root = map.getCell(0, 0);
		m_priorityQueue = new PriorityQueue<Cell>((node1, node2)->
				(heuristic(node1) + node1.getCost() - heuristic(node2) - node2.getCost()));
		m_solution = "";
		m_cost = 0;
	}

	@Override
	public void search()
	{
		m_priorityQueue.add(m_root);
		//System.out.println(m_root + " ===> ENTERED TO LIST");
		for (int i = 0; !m_priorityQueue.isEmpty() && i < (m_size * m_size * MAX_COST); i++)
		{
			//1.
			Cell node = m_priorityQueue.poll();
			//2.
			if (node.getType() == Cell.GOAL)
			{
				System.out.println("GOAL FOUND!! HEYDAD!!");
				printToOutput(backTraceSolution(node));
				return;
			}
			//3.
			if (m_priorityQueue.contains(node))
			{
				continue;
			}
			//4. 5.
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);

			//6.
			for (Cell child : childrenList)
			{

				if ((m_map.isValidMove(node, child)) && (!m_priorityQueue.contains(child)))
				{
					//System.out.println(node + " ===> " + child);
					//System.out.println(child + " ===> ENTERED TO LIST");
					//1.
					child.setCost(child.getCost() + node.getCost());
					//2.
					child.setParent(node);
					//3.
					child.setHeuristic(heuristic(child));
					m_priorityQueue.add(child);
				}
			}
			m_priorityQueue.remove(node);
			//System.out.println(node + " ===> REMOVED FROM LIST");
		}
		System.out.println("NOT FOUND!");
		printToOutput(NO_PATH);
	}

	private String backTraceSolution(Cell node)
	{
		String solution = "";
		// The start cell - return.
		if (node.getType() == Cell.START)
		{
			return solution;
		}
		// Not the start cell - go to the parent.
		else
		{
			solution = backTraceSolution(node.getParent());
		}
		// Print
		return solution += addStep(node.getParent(), node);
	}

	// Heuristic on this cell. Calculates the
	private int heuristic(Cell cell)
	{
		return Math.max(m_size - 1 - cell.getXVal(), m_size - 1 - cell.getYVal());
	}
}
