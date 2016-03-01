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

public class Twitter {
	static private HashSet<TweetEntry> allTweets = new HashSet<TweetEntry>();
	static private HashSet<StateEntry> allStates = new HashSet<StateEntry>();
	static private HashSet<SentimentEntry> allSentiments = new HashSet<SentimentEntry>();

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Error: Bad format parameters");
			return;
		}

		LoadAllTweets();
		LoadAllStates();
		LoadAllSentiments();
	}

	public static void LoadAllTweets() {
		IReader reader = null;
		allTweets.clear();
		try {
			reader = new FileReade("all_tweets.txt");
			List<String> lines = reader.ReadByBetween(0, 300);
			for (String str : lines) {
				IParser<String, TweetEntry> parser = new TweetsParser();
				allTweets.add(parser.Parse(str));
			}
			System.out.println("Tweets load successfully. Loaded rows " + allTweets.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}

	public static void LoadAllStates() {
		IReader reader = null;
		allStates.clear();
		try {
			reader = new FileReade("states.json");
			JSONObject json = new JSONObject(reader.ReadLine());
			Iterator<String> keys = json.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				IParser<JSONArray, StateEntry> states = new StatesParser();
				StateEntry state = states.Parse(json.getJSONArray(key));
				state.setName(key);
				allStates.add(state);
			}

			System.out.println("States load successfully. Loaded rows " + allStates.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
	
	public static void LoadAllSentiments() {
		IReader reader = null;
		allSentiments.clear();
		try {
			reader = new FileReade("sentiments.csv");
			List<String> lines = reader.ReadByBetween(0, 1000);
			for (String str : lines) {
				IParser<String, SentimentEntry> parser = new SentimentsParsers();
				allSentiments.add(parser.Parse(str));
			}
			System.out.println("Settiments load successfully. Loaded rows " + allSentiments.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
}
