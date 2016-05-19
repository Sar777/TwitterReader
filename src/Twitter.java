import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.javatuples.Pair;

import com.mysql.jdbc.Connection;

import Common.DataTypes;
import Data.SentimentEntry;
import Data.StateEntry;
import Data.TweetEntry;
import Frame.Frame;
import Reports.ReportHashTag;
import Reports.ReportStateTweets;
import Reports.ReportTweetSentiments;
import Reports.TypeReports;
import Reports.Generators.IReportsGenerator;
import Reports.Settings.SettingHashTag;
import Reports.Settings.SettingStateTweets;
import Reports.Settings.SettingTweetSentiments;

public class Twitter {
	static private HashSet<TweetEntry> allTweets;
	static private HashSet<StateEntry> allStates;
	static private HashSet<SentimentEntry> allSentiments;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		if (args.length < 3) {
			System.out.println("Error: Bad format parameters");
			return;
		}
		
		Future<HashSet<?>> f1 = LoaderMgr.getInstance().LoadData(DataTypes.DATA_ALL_TWEETS, "all_tweets.txt");
		Future<HashSet<?>> f2 = LoaderMgr.getInstance().LoadData(DataTypes.DATA_ALL_STATES, "states.json");
		Future<HashSet<?>> f3 = LoaderMgr.getInstance().LoadData(DataTypes.DATA_ALL_SENTINMENTS, "sentiments.csv");
		
		try {
			allTweets = new HashSet<TweetEntry>((HashSet<TweetEntry>)f1.get());
			allStates = new HashSet<StateEntry>((HashSet<StateEntry>)f2.get());
			allSentiments = new HashSet<SentimentEntry>((HashSet<SentimentEntry>)f3.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			SaveDataDB(DataTypes.DATA_ALL_TWEETS);
			SaveDataDB(DataTypes.DATA_ALL_STATES);
			SaveDataDB(DataTypes.DATA_ALL_SENTINMENTS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		Thread t1 = new Thread(() -> generateReport(TypeReports.REPORT_TWEETS_BY_HASH_TAG, true));
		t1.start();
		t1.join();
		/*Thread t2 = new Thread(() -> generateReport(TypeReports.REPORT_STATE_MAX_TWEETS, false));
		t2.start();
		t2.join();
		Thread t3 = new Thread(() -> generateReport(TypeReports.REPORT_TWEETS_SENTIMENTS, false));
		t3.start();
		t3.join();*/
		new Frame(allStates);
	}
	
	public static void generateReport(TypeReports type, boolean db) {
		IReportsGenerator<?,?> report = null;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		switch (type) {
			case REPORT_TWEETS_BY_HASH_TAG:
			{
				System.out.println("You selected report by hash tag");
				try {
					System.out.println("Input hash tag: ");
					String hashtag = bufferRead.readLine();
					
					// By database
					if (db) {
						Connection conn = Database.getConnection();
						report = new ReportHashTag(allTweets).generate(new SettingHashTag(hashtag), conn);
						conn.close();
					}
					else
						report = new ReportHashTag(allTweets).generate(new SettingHashTag(hashtag));

					if (report != null && ((ReportHashTag)report).getResults().isEmpty()) {
						System.out.println("Not found");
						break;
					}
					System.out.println("Result:");
					for (TweetEntry tweet : ((ReportHashTag)report).getResults())
						System.out.println(tweet.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
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
					
					// By database
					if (db) {
						Connection conn = Database.getConnection();
						report = new ReportTweetSentiments(allTweets, allSentiments).generate(new SettingTweetSentiments(formatter.parse(firstDate), formatter.parse(endDate)), conn);
						conn.close();
					}
					else
						report = new ReportTweetSentiments(allTweets, allSentiments).generate(new SettingTweetSentiments(formatter.parse(firstDate), formatter.parse(endDate)));

					System.out.println("Result: " + ((ReportTweetSentiments)report).getResult());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
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

					// By database
					if (db) {
						Connection conn = Database.getConnection();
						report = new ReportStateTweets(allTweets, allStates).generate(new SettingStateTweets(formatter.parse(firstDate), formatter.parse(endDate)), conn);
						conn.close();
					}
					else
						report = new ReportStateTweets(allTweets, allStates).generate(new SettingStateTweets(formatter.parse(firstDate), formatter.parse(endDate)));
					
					Pair<String, Integer> result = ((ReportStateTweets)report).getResult();			
					if (result.getValue0().isEmpty()) {
						System.out.println("Not found");
						break;
					}
					System.out.println("Result: " + result.getValue0() + " -> " + result.getValue1());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			default:
				System.out.println("Not supported it report");
				return;
		}
	}
	
	private static void SaveDataDB(DataTypes type) throws SQLException {
		Connection conn = Database.getConnection();
		if (conn == null)
			return;
		
		switch (type) {
			case DATA_ALL_TWEETS:
			{
				if (allTweets.isEmpty())
					break;
				
				conn.prepareStatement("TRUNCATE `tweets`").executeUpdate();
				for (TweetEntry tweetEntry : allTweets)
					tweetEntry.SaveToDB(conn);
				break;
			}
			case DATA_ALL_STATES:
			{
				if (allStates.isEmpty())
					break;
				
				conn.prepareStatement("TRUNCATE `states`").executeUpdate();
				for (StateEntry stateEntry : allStates)
					stateEntry.SaveToDB(conn);
				break;
			}
			case DATA_ALL_SENTINMENTS:
			{
				if (allSentiments.isEmpty())
					break;
				
				conn.prepareStatement("TRUNCATE `sentiments`").executeUpdate();
				for (SentimentEntry sentimentEntry : allSentiments)
					sentimentEntry.SaveToDB(conn);
				break;
			}
			default:
				throw new UnsupportedOperationException("Unsupported data type: " + type);
		}
		
		conn.close();
		System.out.println("Save " + type.toString() + " success");
	}
}
