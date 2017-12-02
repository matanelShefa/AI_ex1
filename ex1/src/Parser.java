import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Parser
{
	// Members
	private String m_inputFile;
	private int m_size;
	private String m_algorithm;
	private String m_typeString;

	// Constructor
	Parser(String inputFile)
	{
		m_typeString = new String();
		m_inputFile = inputFile;
		try (BufferedReader reader = new BufferedReader(new FileReader(m_inputFile)))
		{
			String fileCurrentLine;
			m_algorithm = reader.readLine();
			m_size = Integer.parseInt(reader.readLine());

			int i = 0;
			while ((fileCurrentLine = reader.readLine()) != null)
			{
				System.out.println(fileCurrentLine);

				for (int j = 0; j < m_size; j++)
				{
					m_typeString += fileCurrentLine.charAt(j);
				}

				i++;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String parseLine()
	{
		String fileCurrentLine;
		try (BufferedReader reader = new BufferedReader(new FileReader(m_inputFile)))
		{
			fileCurrentLine = reader.readLine();
			System.out.println("fileCurrentLine: " + fileCurrentLine);
			return fileCurrentLine;
			//return reader.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
			return "EXCEPTION!";
		}
	}

	// Getter
	public String getTypeString() { return m_typeString; }

	// Getter
	public String getAlgorithm() { return m_algorithm; }

	// Getter
	public int getSize() { return m_size; }
}
