package Data;

public class SentimentEntry {
	private String _word;
	private double _sentiment;
	
	public SentimentEntry(String str) {
		String[] split = str.split(",");
		setWord(split[0]);
		setSentiment(Double.parseDouble(split[1]));
	}

	public String getWord() {
		return _word;
	}

	public void setWord(String _word) {
		this._word = _word;
	}

	public double getSentiment() {
		return _sentiment;
	}

	public void setSentiment(double _sentiment) {
		this._sentiment = _sentiment;
	}
}
