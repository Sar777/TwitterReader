import java.io.FileNotFoundException;
import java.util.List;

public class Twitter {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Error: Bad format parameters");
			return;
		}

		Reader reader = null;
		try {
			reader = new FileReade(args[0]);
			List<String> lines = reader.ReadByBetween(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			for (String line : lines) {
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) { }
		finally {
			((FileReade)reader).Close();
		}
	}

}
