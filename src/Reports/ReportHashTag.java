package Reports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingHashTag;

public class ReportHashTag implements IReportsGenerator<SettingHashTag, ReportHashTag> {
	private HashSet<TweetEntry> tweets;
	private List<TweetEntry> result;
	
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

	public HashSet<TweetEntry> getTweets() {
		return tweets;
	}

	public void setTweets(HashSet<TweetEntry> tweets) {
		this.tweets = tweets;
	}

	public List<TweetEntry> getResult() {
		return result;
	}

	public void setResult(List<TweetEntry> result) {
		this.result = result;
	}
}
