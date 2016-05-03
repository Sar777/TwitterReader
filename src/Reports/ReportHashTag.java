package Reports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingHashTag;

/**
 * The Class ReportHashTag. Generate report about hash tag in tweets
 */
public class ReportHashTag implements IReportsGenerator<SettingHashTag, ReportHashTag> {
	private HashSet<TweetEntry> tweets;
	private List<TweetEntry> result;

	/**
	 * Create a new report by hash tag.
	 *
	 * @param tweets the information about tweets
	 */
	public ReportHashTag(HashSet<TweetEntry> tweets) {
		this.setTweets(tweets);
		this.result = new ArrayList<TweetEntry>();
	}

	@Override
	public ReportHashTag generate(SettingHashTag setting) {
		result.clear();
		for (TweetEntry tweet : tweets) {
			if (tweet.getText().indexOf(setting.getTag()) != -1)
				result.add(tweet);
		}
		return this;
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
	 * @param tweets the set new tweets array
	 */
	public void setTweets(HashSet<TweetEntry> tweets) {
		this.tweets = tweets;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(List<TweetEntry> result) {
		this.result = result;
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<TweetEntry> getResults() {
		return result;
	}
}
