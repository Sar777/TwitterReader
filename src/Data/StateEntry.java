package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.javatuples.*;

public class StateEntry {
	private String name;
	private Map<Integer, List<Pair<Double, Double>>> cordsList;

	public StateEntry() {
		this.cordsList = new HashMap<Integer, List<Pair<Double, Double>>>();
	}

	public void addCoordinate(int group, double x, double y) {
		if (!cordsList.containsKey(group))
			cordsList.put(group, new ArrayList<Pair<Double, Double>>());

		cordsList.get(group).add(new Pair<Double, Double>(x, y));
	}

	public Map<Integer, List<Pair<Double, Double>>> getCoordinates() {
		return cordsList;
	}

	public String getName() {
		return name;
	}

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
