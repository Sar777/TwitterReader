package Data;

import java.util.regex.*;

public class TweetEntry {
	private double _latitude;
	private double _longitude;

	private String _date;
	private String _text;
	
	public TweetEntry(double _latitude, double _longitude, String _date, String _text) {
		this.setLatitude(_latitude);
		this.setLongitude(_longitude);
		this.setDate(_date);
		this.setText(_text);
	}

	public TweetEntry(String str) {
		Matcher m = Pattern.compile("^\\[([0-9-.]+), ([0-9-.]+)\\]\\t[0-9]\\t([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+)\\t(.*)").matcher(str);
		if (!m.matches())
			return;

		this.setLatitude(Double.parseDouble(m.group(1)));
		this.setLongitude(Double.parseDouble(m.group(2)));
		this.setDate(m.group(3));
		this.setText(m.group(4));
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double _latitude) {
		this._latitude = _latitude;
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double _longitude) {
		this._longitude = _longitude;
	}

	public String getDate() {
		return _date;
	}

	public void setDate(String _date) {
		this._date = _date;
	}

	public String getText() {
		return _text;
	}

	public void setText(String _text) {
		this._text = _text;
	}
}
