package Data;
import java.util.Date;

/**
 * The Class TweetEntry. Information about tweet.
 */
public class TweetEntry {
	private double latitude;
	private double longitude;

	private Date date;
	private String text;
	
	/**
	 * Instantiates a new tweet entry.
	 */
	public TweetEntry() {
	}

	/**
	 * Instantiates a new tweet entry.
	 *
	 * @param latitude the tweet latitude
	 * @param longitude the tweet longitude
	 * @param date the tweet date
	 * @param text the tweet text
	 */
	public TweetEntry(double latitude, double longitude, Date date, String text) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setDate(date);
		this.setText(text);
	}

	/**
	 * Gets the tweet latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the tweet latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the tweet longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the tweet longitude.
	 *
	 * @param longitude the new tweet longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the tweet date.
	 *
	 * @return the tweet date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the tweet date.
	 *
	 * @param date the new tweet date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the tweet text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the tweet text.
	 *
	 * @param text the new tweet text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "[" + latitude + ", " + longitude + "] " + date.toString() + " " + text;
	}
}
