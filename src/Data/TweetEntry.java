package Data;
import java.util.Date;

public class TweetEntry {
	private double latitude;
	private double longitude;

	private Date date;
	private String text;
	
	public TweetEntry() {
	}
	
	public TweetEntry(double latitude, double longitude, Date date, String text) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setDate(date);
		this.setText(text);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String _text) {
		this.text = _text;
	}
}
