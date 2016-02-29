package Data;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.*;

public class StateEntry {
	private String _name;
	private List<Triplet<Integer, Double, Double>> _cordsList;
	
	public StateEntry(String str) {
		this.setName(str);
		this._cordsList = new ArrayList<Triplet<Integer, Double, Double>>();
	}
	
	public void addCoordinate(int group, double x, double y) {
		_cordsList.add(new Triplet<Integer, Double, Double>(group, x, y));
	}

	public List<Triplet<Integer, Double, Double>> getCoordinates(){
		return _cordsList;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
}
