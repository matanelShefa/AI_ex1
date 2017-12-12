import java.io.*;

/**
 * Created by Matanel on 05/12/2017.
 * This abstract class use to define a search algorithm.
 * Every search algorithm can inherit from this class.
 */
public abstract class Searcher implements Algorithm
{
	// Finals
	protected static final String NO_PATH = "no path";
	private static final String OUTPUT_FILE = "output.txt";

	/**
	 * Add one step to the solution string.
	 * @param from The source cell.
	 * @param to The destination cell.
	 * @return String that tells the step need to be done in order to go
	 * from the source cell to the destination cell.
	 */
	public String addStep(Cell from, Cell to)
	{
		int fromX = from.getXVal();
		int fromY = from.getYVal();
		int toX = to.getXVal();
		int toY = to.getYVal();
		String step = "";

		// Generate the string.
		if (from.getType() != Cell.START)
		{
			step = "-";
		}

		if (fromY - toY == 1)
		{
			step += "L";
		}
		else if (fromY - toY == -1)
		{
			step += "R";
		}

		if (fromX - toX == 1)
		{
			step += "U";
		}
		else if (fromX - toX == -1)
		{
			step += "D";
		}

		return step;
	}

	/**
	 * Print the solution string to the output file.
	 * @param stringToPrint The solution string to print.
	 */
	public void printToOutput(String stringToPrint)
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(OUTPUT_FILE), "utf-8"))) {
			writer.write(stringToPrint);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
