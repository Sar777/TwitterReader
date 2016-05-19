package Parsers;

import java.sql.ResultSet;

import Data.SentimentEntry;

/**
 * The Class SentimentsParsers. Parse tweet sentiments.
 */
public class SentimentsParsers implements IParser<String, SentimentEntry> {

	@Override
	public SentimentEntry parse(String data) {
		SentimentEntry entry = new SentimentEntry();
		String[] split = data.split(",");
		entry.setWord(split[0]);
		entry.setSentiment(Float.parseFloat(split[1]));
		return entry;
	}

	@Override
	public SentimentEntry parse(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
