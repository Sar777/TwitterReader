package Reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.javatuples.Pair;

import com.mysql.jdbc.Connection;

import Common.Misc;
import Data.StateEntry;
import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingStateTweets;

/**
 * The Class ReportStateTweets. Generate report about state tweets.
 */
public class ReportStateTweets implements IReportsGenerator<SettingStateTweets, ReportStateTweets> {
	private HashSet<TweetEntry> tweets;
	private HashSet<StateEntry> states;
	private Pair<String, Integer> state;

	public ReportStateTweets(HashSet<TweetEntry> tweets, HashSet<StateEntry> states) {
		this.tweets = tweets;
		this.states = states;
		this.state = null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ReportStateTweets generate(SettingStateTweets setting) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (TweetEntry tweet : tweets) {
			if (tweet.getDate().getTime() < setting.getStart().getTime() || tweet.getDate().getTime() > setting.getEnd().getTime())
				continue;
			for (StateEntry stateEntry : states) {
				for (Entry<Integer, List<Pair<Double, Double>>> cords : stateEntry.getCoordinates().entrySet()) {
					if (!Misc.isInPolygon(cords.getValue(), tweet.getLatitude(), tweet.getLongitude()))
						continue;
			
					if (!map.containsKey(stateEntry.getName()))
						map.put(stateEntry.getName(), 1);
					else
						map.put(stateEntry.getName(), map.get(stateEntry.getName()) + 1);
				}
			}
		}
		
		if (map.isEmpty()) {
			return this;
		}
		
		List list = new ArrayList(map.entrySet());
		Collections.sort(list, new Comparator() {
		   public int compare(Object obj1, Object obj2){
		   return ((Comparable)((Map.Entry)(obj1)).getValue()).compareTo(((Map.Entry)(obj2)).getValue()); }});
		
		Map.Entry<String, Integer> val = (Entry<String, Integer>)list.get(list.size() - 1);
		this.state = new Pair<String, Integer>(val.getKey(), val.getValue());
		return this;
	}
	
	@Override
	public ReportStateTweets generate(SettingStateTweets setting, Connection conn) {
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
	 * Gets the states.
	 *
	 * @return the states
	 */
	public HashSet<StateEntry> getStates() {
		return states;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Pair<String, Integer> getResult() {
		return state;
	}

	/**
	 * Sets the result.
	 *
	 * @param state the state
	 */
	public void setResult(Pair<String, Integer> state) {
		this.state = state;
	}
}
