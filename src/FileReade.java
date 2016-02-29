import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReade implements IReader {
	java.io.Reader reader;

	public FileReade(String filename) throws FileNotFoundException {
		this.reader = new FileReader(filename);
	}
	
	@Override
	public List<String> ReadAll() {
		List<String> lines = new ArrayList<String>();
		String line;
		 try (BufferedReader br = new BufferedReader(reader)) {
			while ((line = br.readLine()) != null) {
				lines.add(line);
			 }
		 } catch (Exception e) {
			// TODO: handle exception
		}
		return lines;
	}
	
	@Override
	public String ReadLine() {
		 try (BufferedReader br = new BufferedReader(reader)) {
			 return br.readLine();
		 } catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	@Override
	public List<String> ReadByBetween(int start, int count) {
		 List<String> lines = new ArrayList<String>();
		 if (start < 0 || count <= 0) {
			 System.out.println("Error: ReadByBetween bad parameters");
			 return lines;
		 }
		 
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
