import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.javatuples.Pair;
import org.json.*;

import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;
import Frame.Frame;
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

		loadAllTweets("all_tweets.txt");
		loadAllStates("states.json");
		loadAllSentiments("sentiments.csv");
		
		//generateReport(TypeReports.REPORT_TWEETS_BY_HASH_TAG);
		
		StateEntry entry = allStates.iterator().next();
		Frame f = new Frame(entry.getCoordinates().get(0));
	}

	// Все твиты
	public static void loadAllTweets(String fileName) {
		IReader reader = null;
		allTweets.clear();
		try {
			reader = new FileReade(fileName);
			List<String> lines = reader.ReadByBetween(0, 1200);
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
	}

	// Все штаты
	public static void loadAllStates(String fileName) {
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
	public static void loadAllSentiments(String fileName) {
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
	
	public static void generateReport(TypeReports type) {
		IReportsGenerator<?,?> report = null;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		switch (type) {
			case REPORT_TWEETS_BY_HASH_TAG:
			{
				System.out.println("You selected report by hash tag");
				try {
					System.out.println("Input hash tag: ");
					String hashtag = bufferRead.readLine();
					report = new ReportHashTag(allTweets).generate(new SettingHashTag(hashtag));
					System.out.println("Result:");
					for (TweetEntry tweet : ((ReportHashTag)report).getResult()) {
						System.out.println(tweet.toString());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case REPORT_TWEETS_SENTIMENTS:
			{
				System.out.println("You selected report tweets sentiments");
				try {
					System.out.println("Input start date(format: XXXX-XX-XX XX:XX:XX): ");
					String firstDate = bufferRead.readLine();
					System.out.println("Input end date(format: XXXX-XX-XX XX:XX:XX): ");
					String endDate = bufferRead.readLine();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					report = new ReportTweetSentiments(allTweets, allSentiments).generate(new SettingTweetSentiments(formatter.parse(firstDate), formatter.parse(endDate)));
					System.out.println("Result: " + ((ReportTweetSentiments)report).getResult());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case REPORT_STATE_MAX_TWEETS:
			{
				System.out.println("You selected report state max tweets");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					System.out.println("Input start date(format: XXXX-XX-XX XX:XX:XX): ");
					String firstDate = bufferRead.readLine();
					System.out.println("Input end date(format: XXXX-XX-XX XX:XX:XX): ");
					String endDate = bufferRead.readLine();
					report = new ReportStateTweets(allTweets, allStates).generate(new SettingStateTweets(formatter.parse(firstDate), formatter.parse(endDate)));
					Pair<String, Integer> result = ((ReportStateTweets)report).getResult();
					System.out.println("Result: " + result.getValue0() + " -> " + result.getValue1());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			default:
				System.out.println("Not supported it report");
				return;
		}
	}
}
