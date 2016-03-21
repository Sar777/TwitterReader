import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.json.*;

import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;
import Parsers.IParser;
import Parsers.SentimentsParsers;
import Parsers.StatesParser;
import Parsers.TweetsParser;
import Reports.ReportHashTag;
import Reports.ReportStateTweets;
import Reports.ReportTweetSentiments;
import Reports.TypeReports;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingHashTag;
import Reports.Settings.SettingStateTweets;
import Reports.Settings.SettingTweetSentiments;

public class Twitter {
	static private HashSet<TweetEntry> allTweets = new HashSet<TweetEntry>();
	static private HashSet<StateEntry> allStates = new HashSet<StateEntry>();
	static private HashSet<SentimentEntry> allSentiments = new HashSet<SentimentEntry>();

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Error: Bad format parameters");
			return;
		}

		LoadAllTweets("all_tweets.txt");
		LoadAllStates("states.json");
		LoadAllSentiments("sentiments.csv");
	}

	// Все твиты
	public static void LoadAllTweets(String fileName) {
		IReader reader = null;
		allTweets.clear();
		try {
			reader = new FileReade(fileName);
			List<String> lines = reader.ReadByBetween(0, 100);
			for (String str : lines)
				allTweets.add(new TweetsParser().parse(str));
			System.out.println("Tweets load successfully. Loaded rows " + allTweets.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}

	// Все штаты
	public static void LoadAllStates(String fileName) {
		IReader reader = null;
		allStates.clear();
		try {
			reader = new FileReade(fileName);
			JSONObject json = new JSONObject(reader.ReadLine());
			Iterator<String> keys = json.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				IParser<JSONArray, StateEntry> states = new StatesParser();
				StateEntry state = states.parse(json.getJSONArray(key));
				state.setName(key);
				allStates.add(state);
			}

			System.out.println("States load successfully. Loaded rows " + allStates.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
	
	// Все эмоциональные состояния
	public static void LoadAllSentiments(String fileName) {
		IReader reader = null;
		allSentiments.clear();
		try {
			reader = new FileReade(fileName);
			List<String> lines = reader.ReadByBetween(0, 1000);
			for (String str : lines)
				allSentiments.add(new SentimentsParsers().parse(str));
			System.out.println("Settiments load successfully. Loaded rows " + allSentiments.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
	
	public static void GenerateReport(TypeReports type) {
		@SuppressWarnings("unused")
		IReportsGenerator<?,?> report = null;
		switch (type) {
			case REPORT_TWEETS_BY_HASH_TAG:
				report = new ReportHashTag(allTweets).generate(new SettingHashTag("#hashtag"));
				break;
			case REPORT_TWEETS_SENTIMENTS:
				//report = new ReportTweetSentiments(allTweets, allSentiments).generate(new SettingTweetSentiments());
				break;
			case REPORT_STATE_MAX_TWEETS:
				//report = new ReportStateTweets(allTweets, allStates).generate(new SettingStateTweets());
				break;
			default:
				System.out.println("Not supported it report");
				return;
		}
		
		//report.get
	}
}
