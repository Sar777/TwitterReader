package Reports.Settings;

/**
 * The Class SettingHashTag. Settings for generate report by hash tag.
 */
public class SettingHashTag {
	private String tag;
	
	/**
	 * Instantiates a new setting hash tag.
	 *
	 * @param tag the tag
	 */
	public SettingHashTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
