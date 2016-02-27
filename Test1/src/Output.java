import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output {

	public static void write2File(String projectName, String fileName, String content) {
		BufferedWriter bw = null;

		try {
			// APPEND MODE SET HERE
			bw = new BufferedWriter(new FileWriter(projectName+"-"+fileName, true));
			bw.write(content);
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
		} // end try/catch/finally

	}
}
