import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Parser
{
	// Members
	private int m_size;
	private String m_algorithm;
	private String m_typeString;

	// Constructor
	Parser(String inputFile)
	{
		m_typeString = new String();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
		{
			String fileCurrentLine;
			m_algorithm = reader.readLine();
			m_size = Integer.parseInt(reader.readLine());

			while ((fileCurrentLine = reader.readLine()) != null)
			{
				for (int j = 0; j < m_size; j++)
				{
					m_typeString += fileCurrentLine.charAt(j);
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// Getter
	public String getTypeString() { return m_typeString; }

	// Getter
	public String getAlgorithm() { return m_algorithm; }

	// Getter
	public int getSize() { return m_size; }
}
