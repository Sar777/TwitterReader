package Reports;

import java.util.HashSet;

import com.mysql.jdbc.Connection;

import Data.SentimentEntry;
import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingTweetSentiments;

/**
 * The Class ReportTweetSentiments. Generate report about summary tweet sentiments.
 */
public class ReportTweetSentiments implements IReportsGenerator<SettingTweetSentiments, ReportTweetSentiments> {
	private HashSet<TweetEntry> tweets;
	private HashSet<SentimentEntry> sentiments;
	
	private double result;
	
	/**
	 * Create a new report by tweet sentiments.
	 *
	 * @param tweets the all tweets
	 * @param sentiments the all sentiments
	 */
	public ReportTweetSentiments(HashSet<TweetEntry> tweets, HashSet<SentimentEntry> sentiments) {
		this.setTweets(tweets);
		this.setSentiments(sentiments);
		this.result = 0.0f;
	}
	
	@Override
	public ReportTweetSentiments generate(SettingTweetSentiments setting) {
		result = 0.0f;
		for (TweetEntry tweet : tweets) {
			if (tweet.getDate().getTime() >= setting.getStart().getTime() && tweet.getDate().getTime() <= setting.getEnd().getTime()) {
				String[] splitTweet = tweet.getText().split(" ");
				for (String word : splitTweet) {
					result += getSentimentByWord(word);
				}
			}
		}
		
		return this;
	}
	

	@Override
	public ReportTweetSentiments generate(SettingTweetSentiments setting, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the tweets.
	 *
	 * @return the tweets
	 */
	public HashSet<TweetEntry> getTweets() {
		return tweets;
	}

	/**
	 * Sets the tweets.
	 *
	 * @param tweets the new tweets
	 */
	public void setTweets(HashSet<TweetEntry> tweets) {
		this.tweets = tweets;
	}

	/**
	 * Gets the sentiments.
	 *
	 * @return the sentiments
	 */
	public HashSet<SentimentEntry> getSentiments() {
		return sentiments;
	}

	/**
	 * Sets the sentiments.
	 *
	 * @param sentiments the set all sentiments
	 */
	public void setSentiments(HashSet<SentimentEntry> sentiments) {
		this.sentiments = sentiments;
	}

	/**
	 * Gets report the result.
	 *
	 * @return the result
	 */
	public double getResult() {
		return result;
	}

	/**
	 * Gets the sentiment by word.
	 *
	 * @param word the word
	 * @return the sentiment by word
	 */
	double getSentimentByWord(String word) {
		for (SentimentEntry sentiment : sentiments)
			if (sentiment.getWord().equals(word))
				return sentiment.getSentiment();
		
		return 0.0f;
	}
}
