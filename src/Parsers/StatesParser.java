package Parsers;

import org.json.JSONArray;

import Data.StateEntry;

public class StatesParser implements IParser<JSONArray, StateEntry> {
	@Override
	public StateEntry parse(JSONArray data) {
		StateEntry state = new StateEntry();
		for (int i = 0; i < data.length(); ++i) {
			JSONArray groupJson = new JSONArray(data.get(i).toString());
			for (int j = 0; j < groupJson.length(); ++j) {
				JSONArray jsonCoord = new JSONArray(groupJson.get(j).toString());
				if (data.length() == 1) {
					double x = jsonCoord.getDouble(0);
					double y = jsonCoord.getDouble(1);
					state.addCoordinate(i, x, y);
					continue;
				}

				for (int k = 0; k < jsonCoord.length(); ++k) {
					JSONArray jsonArray = new JSONArray(jsonCoord.get(k).toString());
					if (jsonArray.length() == 2) {
						double x = jsonArray.getDouble(0);
						double y = jsonArray.getDouble(1);
						state.addCoordinate(i, x, y);
					}
				}
			}
		}
		
		return state;
	}

}
