package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.javatuples.*;

/**
 * The Class StateEntry. Information about state
 */
public class StateEntry {
	private String name;
	private Map<Integer, List<Pair<Double, Double>>> cordsList;

	/**
	 * Instantiates a new state entry.
	 */
	public StateEntry() {
		this.cordsList = new HashMap<Integer, List<Pair<Double, Double>>>();
	}

	/**
	 * Adds the coordinate.
	 *
	 * @param group the coordinate group
	 * @param x the position x
	 * @param y the position y
	 */
	public void addCoordinate(int group, double x, double y) {
		if (!cordsList.containsKey(group))
			cordsList.put(group, new ArrayList<Pair<Double, Double>>());

		cordsList.get(group).add(new Pair<Double, Double>(x, y));
	}

	/**
	 * Gets the all coordinates.
	 *
	 * @return the coordinates
	 */
	public Map<Integer, List<Pair<Double, Double>>> getCoordinates() {
		return cordsList;
	}

	/**
	 * Gets the state name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the state name.
	 *
	 * @param _name the new name
	 */
	public void setName(String _name) {
		this.name = _name;
	}

	@Override
	public String toString() {
		String data = "";
		data += name + "\n";
		int group = -1;
		for (Entry<Integer, List<Pair<Double, Double>>> map : cordsList.entrySet()) {
			if (group != map.getKey()) {
				data += "Group: " + map.getKey() + "\n";
				group = map.getKey();
			}
			for (Pair<Double, Double> cord : map.getValue()) {
				data += "\t{ " + cord.getValue0() + ", " + cord.getValue1() + "}\n";
			}
		}
		return data;
	}
}
