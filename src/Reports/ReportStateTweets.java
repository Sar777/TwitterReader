package Reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.javatuples.Pair;

import Common.Misc;
import Data.StateEntry;
import Data.TweetEntry;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingStateTweets;

public class ReportStateTweets implements IReportsGenerator<SettingStateTweets, ReportStateTweets> {
	private HashSet<TweetEntry> tweets;
	private HashSet<StateEntry> states;
	private StateEntry state;

	public ReportStateTweets(HashSet<TweetEntry> tweets, HashSet<StateEntry> states) {
		this.tweets = tweets;
		this.states = states;
		this.state = null;
	}

	@Override
	public ReportStateTweets generate(SettingStateTweets setting) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int count = 0;
		for (TweetEntry tweet : tweets) {
			///if (tweet.getDate().getTime() < setting.getStart().getTime() || tweet.getDate().getTime() > setting.getEnd().getTime())
			///	continue;
			for (StateEntry stateEntry : states) {
				for (Entry<Integer, List<Pair<Double, Double>>> cords : stateEntry.getCoordinates().entrySet()) {
					if (!Misc.isInPolygon(cords.getValue(), tweet.getLatitude(), tweet.getLongitude()))
						continue;

					count += 1;
				}

				map.put(stateEntry.getName(), count);
			}
		}

		for (Entry<String, Integer> temp : map.entrySet()) {
			System.out.println(temp.getKey() + " " + temp.getValue());
		}

		return this;
	}

	public HashSet<TweetEntry> getTweets() {
		return tweets;
	}

	public HashSet<StateEntry> getStates() {
		return states;
	}

	public StateEntry getResult() {
		return state;
	}

	public void setResult(StateEntry state) {
		this.state = state;
	}
}
