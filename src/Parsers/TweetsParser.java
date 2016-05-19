package Parsers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Data.TweetEntry;

/**
 * The Class TweetsParser. Parse tweet.
 */
public class TweetsParser implements IParser<String, TweetEntry> {

	@Override
	public TweetEntry parse(String data) {
		Matcher m = Pattern.compile("^\\[([0-9-.]+), ([0-9-.]+)\\]\\t[0-9]\\t([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+)\\t(.*)").matcher(data);
		if (!m.matches())
			return null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		TweetEntry tweet = new TweetEntry();
		tweet.setLatitude(Double.parseDouble(m.group(2)));
		tweet.setLongitude(Double.parseDouble(m.group(1)));
		try {
			tweet.setDate(formatter.parse(m.group(3)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tweet.setText(m.group(4));
		return tweet;
	}

	@Override
	public TweetEntry parse(ResultSet rs) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TweetEntry tweet = new TweetEntry();
		try {
			tweet.setLatitude(rs.getDouble(1));
			tweet.setLongitude(rs.getDouble(2));
			tweet.setDate(dateFormat.parse(rs.getString(3)));
			tweet.setText(rs.getString(4));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return tweet;
	}
}
