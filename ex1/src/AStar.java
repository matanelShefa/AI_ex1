import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Matanel on 06/12/2017.
 * This class is an implement of the A* algorithm for an Informal search in graph.
 * The algorithm calculates the next step to be the most efficient, summing the
 * real cost with a heuristic prediction.
 */
public class AStar extends Searcher
{
	// Final
	private static final int MAX_COST = 10;

	// Members
	private int m_size;
	private Cell m_root;
	private String m_solution;
	private Map m_map;
	private PriorityQueue<Cell> m_priorityQueue;
	private int m_cost;

	/**
	 * Constructor.
	 * @param map The map of the world to search in.
	 */
	AStar(Map map)
	{
		m_map = map;
		m_size = map.getSize();
		m_root = map.getCell(0, 0);
		// A priority queue for the algorithm. Keeps the most 'cheap' cell in the head of the list.
		m_priorityQueue = new PriorityQueue<Cell>(new Comparator<Cell>()
		{
			@Override
			/**
			 * The compare function for the queue.
			 * @param node1 One node.
			 * @param node2 Other node.
			 * @return If node1 is cheaper - negative. Else - positive.
			 */
			public int compare(Cell node1, Cell node2)
			{
				int priority = node1.getHeuristic() + node1.getCost() - node2.getHeuristic() - node2.getCost();
				if (priority != 0)
				{
					return priority;
				}
				// Same priority - check creation time.
				return node1.getCreationTime() - node2.getCreationTime();
			}
		});

		m_solution = "";
		m_cost = 0;
	}

	@Override
	// The search method. Searches for the goal from the start point of the map.
	public void search()
	{
		m_priorityQueue.add(m_root);
		// Search as long as the priority queue is not empty.
		while (!m_priorityQueue.isEmpty())
		{
			// Take the cheapest node from the priority queue.
			Cell node = m_priorityQueue.poll();
			// Over the limit of cost - stop searching.
			if ((node.getCost()) > (m_size * m_size * MAX_COST))
			{
				break;
			}
			// Node is the goal - finish.
			if (node.getType() == Cell.GOAL)
			{
				// Print to the file.
				backTraceSolution(node);
				printToOutput(m_solution + " " + m_cost);
				return;
			}
			// Don't check any already known node.
			if (m_priorityQueue.contains(node))
			{
				continue;
			}
			// Generate the children list of the node.
			ArrayList<Cell> childrenList = m_map.addChildrenList(node);
			for (Cell child : childrenList)
			{
				// Go over all the valid and yet unknown moves.
				if ((m_map.isValidMove(node, child)) && (!m_priorityQueue.contains(child)))
				{
					// Set the cost and insert to the priority queue.
					child.setCost(child.getCost() + node.getCost());
					child.setParent(node);
					child.setHeuristic(heuristic(child));
					m_priorityQueue.add(child);
				}
			}
		}
		// Print to the file.
		printToOutput(NO_PATH);
	}

	/**
	 * Backtrace the path from the goal to the start.
	 * @param node The node to backtrace the path from.
	 * @return The string that describes the moves to the goal.
	 */
	private String backTraceSolution(Cell node)
	{
		// The start cell - return.
		if (node.getType() == Cell.START)
		{
			return m_solution;
		}
		// Not the start cell - go to the parent.
		else
		{
			m_solution = backTraceSolution(node.getParent());
		}
		// Add the node cost to the path cost.
		m_cost += node.typeToCost(node.getType());
		// Add the step to the solution string.
		return m_solution += addStep(node.getParent(), node);
	}

	/**
	 * Calculates the heuristic prediction cost of this node.
	 * @param node The node to get heuristic cost to.
	 * @return The heuristic cost.
	 */
	private int heuristic(Cell node)
	{
		return Math.max(m_size - 1 - node.getXVal(), m_size - 1 - node.getYVal());
	}
}