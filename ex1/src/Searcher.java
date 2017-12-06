import java.io.*;

/**
 * Created by Matanel on 05/12/2017.
 */
public abstract class Searcher implements Algorithm
{
	// Finals
	private static final String OUTPUT_FILE = "output.txt";

	// Add the current step to the solution string.
	public String addStep(Cell from, Cell to)
	{
		int fromX = from.getXVal();
		int fromY = from.getYVal();
		int toX = to.getXVal();
		int toY = to.getYVal();
		String step = "";

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

	// Print to a the output file.
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
