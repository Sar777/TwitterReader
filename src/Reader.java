import java.util.List;

public interface Reader {
	public List<String> ReadAll();
	public List<String> ReadByBetween(int start, int count);
}