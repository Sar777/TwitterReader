import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.json.*;

import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;

public class Twitter {
	static private HashSet<TweetEntry> _allTweets = new HashSet<TweetEntry>();
	static private HashSet<StateEntry> _allStates = new HashSet<StateEntry>();
	static private HashSet<SentimentEntry> _allSentiments = new HashSet<SentimentEntry>();

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
		_allTweets.clear();
		try {
			reader = new FileReade("all_tweets.txt");
			List<String> lines = reader.ReadByBetween(0, 300);
			for (String str : lines)
				_allTweets.add(new TweetEntry(str));
			System.out.println("Tweets load successfully. Loaded rows " + _allTweets.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}

	public static void LoadAllStates() {
		IReader reader = null;
		_allStates.clear();
		try {
			reader = new FileReade("states.json");
			JSONObject json = new JSONObject(reader.ReadLine());
			Iterator<String> keys = json.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				StateEntry state = new StateEntry(key);
				JSONArray allGroupsJson = json.getJSONArray(key);
				for (int i = 0; i < allGroupsJson.length(); ++i) {
					JSONArray groupJson = new JSONArray(allGroupsJson.get(i).toString());
					for (int j = 0; j < groupJson.length(); ++j) {
						JSONArray jsonCoord = new JSONArray(groupJson.get(j).toString());
						if (allGroupsJson.length() == 1) {
							double x = jsonCoord.getDouble(0);
							double y = jsonCoord.getDouble(1);
							state.addCoordinate(i, x, y);
							continue;
						}

						for (int k = 0; k < jsonCoord.length(); ++k) {
							JSONArray jsonArray = new JSONArray(jsonCoord.get(k).toString());
							if (jsonArray.length() == 2) {
								double x = jsonArray.getDouble(0);
								double y = jsonArray.getDouble(1);
								state.addCoordinate(i, x, y);
							}
						}
					}
				}
				
				_allStates.add(state);
			}

			System.out.println("States load successfully. Loaded rows " + _allStates.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
	
	public static void LoadAllSentiments() {
		IReader reader = null;
		_allSentiments.clear();
		try {
			reader = new FileReade("sentiments.csv");
			List<String> lines = reader.ReadByBetween(0, 1000);
			for (String str : lines)
				_allSentiments.add(new SentimentEntry(str));
			System.out.println("Settiments load successfully. Loaded rows " + _allSentiments.size() + ".");
		} catch (FileNotFoundException e) {
		} finally {
			((FileReade) reader).Close();
		}
	}
}
