import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Matanel on 29/11/2017.
 */
public class Parser
{
	// Data members
	//TODO - replace the file name to semothing resonable.
	private static final String m_fileName = "C:\\Users\\User\\Documents\\מתנאל\\בינה מלאכותית\\תרגילים\\input.txt";
	private int m_size;

	// Constructor
	Parser() { };

	// Parse the input
	void parse()
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(m_fileName))) {

			String fileCurrentLine;

			// Get the algorithm and the
			String algorithm = reader.readLine();
			m_size = Integer.parseInt(reader.readLine());
			System.out.println("m_size = " + m_size);
			Map map = new Map(m_size);

			while ((fileCurrentLine = reader.readLine()) != null) {
				System.out.println(fileCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
