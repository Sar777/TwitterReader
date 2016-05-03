package Reports.Settings;

import java.util.Date;

/**
 * The Class SettingTweetSentiments. Settings for generate report by tweet sentiments.
 */
public class SettingTweetSentiments {
	public Date start;
	public Date end;
	
	/**
	 * Instantiates a new setting tweet sentiments.
	 *
	 * @param start the first date
	 * @param end the last date
	 */
	public SettingTweetSentiments(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Sets the new first date.
	 *
	 * @param date the new first date
	 */
	public void setStart(Date date) {
		this.start = date;
	}
	
	/**
	 * Gets the first date.
	 *
	 * @return the fist date
	 */
	public Date getStart() {
		return this.start;
	}
	
	/**
	 * Sets the last date.
	 *
	 * @param date the new last date
	 */
	public void setEnd(Date date) {
		this.end = date;
	}
	
	/**
	 * Gets the last date.
	 *
	 * @return the last date
	 */
	public Date getEnd() {
		return this.end;
	}
}
