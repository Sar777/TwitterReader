package Reports.Settings;

import java.util.Date;

/**
 * The Class SettingStateTweets. Settings for generate report by tweets in state.
 */
public class SettingStateTweets {
	public Date start;
	public Date end;
	
	/**
	 * Instantiates a new setting state tweets.
	 *
	 * @param start the first date
	 * @param end the last date
	 */
	public SettingStateTweets(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Sets the start.
	 *
	 * @param date the new first date
	 */
	public void setStart(Date date) {
		this.start = date;
	}
	
	/**
	 * Gets the first date.
	 *
	 * @return the start date
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
