package Reports;

import java.util.HashSet;

import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingStateTweets;

public class ReportStateTweets implements IReportsGenerator<SettingStateTweets, ReportStateTweets> {
	private HashSet<TweetEntry> tweets;
	private HashSet<SentimentEntry> sentiments;
	private StateEntry state;
	
	public ReportStateTweets(HashSet<TweetEntry> tweets, HashSet<SentimentEntry> sentiments) {
		this.setTweets(tweets);
		this.setSentiments(sentiments);
		this.state = null;
	}
	
	@Override
	public ReportStateTweets generate(SettingStateTweets setting) {
		// TODO Auto-generated method stub
		return null;
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

	public StateEntry getResult() {
		return state;
	}

	public void setResult(StateEntry state) {
		this.state = state;
	}
}
