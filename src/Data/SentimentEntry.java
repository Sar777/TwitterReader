package Data;

/**
 * The Class SentimentEntry. Information about sentiment.
 */
public class SentimentEntry {
	private String word;
	private double sentiment;

	/**
	 * Gets the sentiment word.
	 *
	 * @return the sentiment word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets the sentiment word.
	 *
	 * @param word the new word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Gets the sentiment.
	 *
	 * @return the sentiment
	 */
	public double getSentiment() {
		return sentiment;
	}

	/**
	 * Sets the sentiment.
	 *
	 * @param sentiment the new sentiment
	 */
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	
	@Override
	public String toString() {
		return word + ", " + sentiment;
	}
}
