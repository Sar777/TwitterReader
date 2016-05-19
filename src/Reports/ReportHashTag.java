package Reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.mysql.jdbc.Connection;

import Data.TweetEntry;
import Parsers.TweetsParser;
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

	@Override
	public ReportHashTag generate(SettingHashTag setting, Connection conn) {
		if (conn == null)
			return null;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT `latitude`, `longitude`, `date`, `tweet` FROM `tweets` WHERE `tweet` LIKE '%" + setting.getTag() + "%'");
			while (rs.next()) {
				TweetEntry tweet = new TweetsParser().parse(rs);
				if (tweet != null)
					result.add(tweet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
