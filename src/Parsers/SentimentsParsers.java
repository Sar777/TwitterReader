package Parsers;

import Data.SentimentEntry;

public class SentimentsParsers implements IParser<String, SentimentEntry> {

	@Override
	public SentimentEntry parse(String data) {
		SentimentEntry entry = new SentimentEntry();
		String[] split = data.split(",");
		entry.setWord(split[0]);
		entry.setSentiment(Double.parseDouble(split[1]));
		return entry;
	}

}
