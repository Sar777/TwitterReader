import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReade implements Reader {
	java.io.Reader reader;

	public FileReade(String filename) throws FileNotFoundException {
		this.reader = new FileReader(filename);
	}
	
	@Override
	public List<String> ReadAll() {
		List<String> lines = new ArrayList<String>();
		return lines;
	}

	@Override
	public List<String> ReadByBetween(int start, int count) {
		 List<String> lines = new ArrayList<String>();
		 String line = null;
		 int position = 0;
		 int read = 0;
		 try (BufferedReader br = new BufferedReader(reader)) {
			while ((line = br.readLine()) != null) {
				if (position++ < start)
					continue;
				
				lines.add(line);
				 
				if (++read >= count)
					break;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
	
	public void Close() {
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
