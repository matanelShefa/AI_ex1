import java.io.*;

/**
 * Created by Matanel on 05/12/2017.
 */
public abstract class Searcher implements Algorithm
{
	// Finals
	public String OUTPUT_FILE = "output.txt";

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

		if ((fromX - toX == 1) && (fromY - toY == 1))
		{
			return step + "LU";
		}
		if ((fromX - toX == 1) && (fromY - toY == 0))
		{
			return step + "U";
		}
		if ((fromX - toX == 1) && (fromY - toY == -1))
		{
			return step + "RU";
		}
		if ((fromX - toX == 0) && (fromY - toY == -1))
		{
			return step + "R";
		}
		if ((fromX - toX == -1) && (fromY - toY == -1))
		{
			return step + "RD";
		}
		if ((fromX - toX == -1) && (fromY - toY == 0))
		{
			return step + "D";
		}
		if ((fromX - toX == -1) && (fromY - toY == 1))
		{
			return step + "LD";
		}
		if ((fromX - toX == 0) && (fromY - toY == 1))
		{
			return step + "L";
		}
		return "";
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
