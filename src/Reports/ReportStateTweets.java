package Reports;

import java.util.HashSet;

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
		// TODO Auto-generated method stub
		return null;
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
