package Reports.Settings;

import java.util.Date;

public class SettingStateTweets {
	public Date start;
	public Date end;
	
	public SettingStateTweets(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	public void setStart(Date date) {
		this.start = date;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public void setEnd(Date date) {
		this.end = date;
	}
	
	public Date getEnd() {
		return this.end;
	}
}
