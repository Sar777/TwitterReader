package Reports;

import java.util.HashSet;

import Data.SentimentEntry;
import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingTweetSentiments;

public class ReportTweetSentiments implements IReportsGenerator<SettingTweetSentiments, ReportTweetSentiments> {
	private HashSet<TweetEntry> tweets;
	private HashSet<SentimentEntry> sentiments;
	
	private double result;
	
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

	public HashSet<TweetEntry> getTweets() {
		return tweets;
	}

	public void setTweets(HashSet<TweetEntry> tweets) {
		this.tweets = tweets;
	}

	public HashSet<SentimentEntry> getSentiments() {
		return sentiments;
	}

	public void setSentiments(HashSet<SentimentEntry> sentiments) {
		this.sentiments = sentiments;
	}

	public double getResult() {
		return result;
	}

	double getSentimentByWord(String word) {
		for (SentimentEntry sentiment : sentiments)
			if (sentiment.getWord().equals(word))
				return sentiment.getSentiment();
		
		return 0.0f;
	}

}
