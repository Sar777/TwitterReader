import java.util.List;

public interface IReader {
	public List<String> ReadAll();
	public String ReadLine();
	public List<String> ReadByBetween(int start, int count);
}