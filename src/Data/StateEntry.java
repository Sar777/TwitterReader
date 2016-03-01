package Data;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.*;

public class StateEntry {
	private String name;
	private List<Triplet<Integer, Double, Double>> cordsList;
	
	public StateEntry() {
		this.cordsList = new ArrayList<Triplet<Integer, Double, Double>>();
	}
	
	public void addCoordinate(int group, double x, double y) {
		cordsList.add(new Triplet<Integer, Double, Double>(group, x, y));
	}

	public List<Triplet<Integer, Double, Double>> getCoordinates(){
		return cordsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}
	
	public String toString() {
		return name;
	}
}
