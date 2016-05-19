package Data;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * The Class SentimentEntry. Information about sentiment.
 */
public class SentimentEntry {
	private String word;
	private float sentiment;

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
	public void setSentiment(float sentiment) {
		this.sentiment = sentiment;
	}
	
	@Override
	public String toString() {
		return word + ", " + sentiment;
	}
	
	/**
	 * Save to sentiment in db.
	 *
	 * @param conn the database connection
	 * @throws SQLException the SQL exception
	 */
	public void SaveToDB(Connection conn) throws SQLException {
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO `sentiments` (`word`, `sentiment`) VALUES (?, ?)");
		statement.setString(1, word);
		statement.setFloat(2, sentiment);
		statement.executeUpdate();
	}
}
