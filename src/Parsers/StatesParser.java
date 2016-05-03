package Parsers;

import org.json.JSONArray;

import Data.StateEntry;

/**
 * The Class StatesParser. Parse state.
 */
public class StatesParser implements IParser<JSONArray, StateEntry> {
	@Override
	public StateEntry parse(JSONArray data) {
		StateEntry state = new StateEntry();
		for (int i = 0; i < data.length(); ++i) {
			JSONArray groupJson = new JSONArray(data.get(i).toString());
			for (int j = 0; j < groupJson.length(); ++j) {
				JSONArray jsonCoord = new JSONArray(groupJson.get(j).toString());
				if (data.length() == 1) {
					state.addCoordinate(i, jsonCoord.getDouble(0), jsonCoord.getDouble(1));
					continue;
				}

				for (int k = 0; k < jsonCoord.length(); ++k) {
					JSONArray jsonArray = new JSONArray(jsonCoord.get(k).toString());
					if (jsonArray.length() == 2) {
						state.addCoordinate(i, jsonArray.getDouble(0), jsonArray.getDouble(1));
					}
				}
			}
		}
		
		return state;
	}

}
