import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import Common.DataTypes;
import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;
import Parsers.IParser;
import Parsers.SentimentsParsers;
import Parsers.StatesParser;
import Parsers.TweetsParser;

public class LoaderMgr {
	private static LoaderMgr instance;
	ExecutorService service;

	public LoaderMgr() {
		service = Executors.newFixedThreadPool(5);
	}

	public static synchronized LoaderMgr getInstance() {
		if (instance == null) {
			instance = new LoaderMgr();
		}
		return instance;
	}

	public Future<HashSet<?>> LoadData(DataTypes data, String filename) {
		switch (data) {
			case DATA_ALL_TWEETS:
				return service.submit(() -> loadAllTweets(filename));
			case DATA_ALL_STATES:
				return service.submit(() -> loadAllStates(filename));
			case DATA_ALL_SENTINMENTS:
				return service.submit(() -> loadAllSentiments(filename));
			default:
				break;
		}

		return null;
	}

	// Все твиты
	private HashSet<TweetEntry> loadAllTweets(String fileName) {
		IReader reader = null;
		HashSet<TweetEntry> allTweets = new HashSet<TweetEntry>();
		try {
			reader = new FileReade(fileName);
			List<String> lines = reader.ReadByBetween(0, 30000);
			for (String str : lines) {
				TweetEntry tweet = new TweetsParser().parse(str);
				if (tweet != null)
					allTweets.add(tweet);
			}
			System.out.println("Tweets load successfully. Loaded rows " + allTweets.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}

		return allTweets;
	}

	// Все штаты
	private HashSet<StateEntry> loadAllStates(String fileName) {
		IReader reader = null;
		HashSet<StateEntry> allStates = new HashSet<StateEntry>();
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

		return allStates;
	}

	// Все эмоциональные состояния
	private HashSet<SentimentEntry> loadAllSentiments(String fileName) {
		IReader reader = null;
		HashSet<SentimentEntry> allSentiments = new HashSet<SentimentEntry>();
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

		return allSentiments;
	}
}
