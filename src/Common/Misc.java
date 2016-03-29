package Common;

import java.util.List;
import org.javatuples.*;

public class Misc {
	static public Boolean isInPolygon(List<Pair<Double, Double>> coord, double x, double y)
	{		
	   Boolean c = false;
	   for (int i = 0, j = coord.size() - 1; i < coord.size(); j = i++) 
	   {
	     if ((
	       (coord.get(i).getValue1() < coord.get(j).getValue1()) && (coord.get(i).getValue1() <= y) && (y <= coord.get(j).getValue1()) &&
	       ((coord.get(j).getValue1() - coord.get(i).getValue1()) * (x - coord.get(i).getValue0()) > (coord.get(j).getValue0() - coord.get(i).getValue0()) * (y - coord.get(i).getValue1()))
	     ) || (
	       (coord.get(i).getValue1() > coord.get(j).getValue1()) && (coord.get(j).getValue1() <= y) && (y <= coord.get(i).getValue1()) &&
	       ((coord.get(j).getValue1() - coord.get(i).getValue1()) * (x - coord.get(i).getValue0()) < (coord.get(j).getValue0() - coord.get(i).getValue0()) * (y - coord.get(i).getValue1()))
	     ))
	       c = !c;
	   }
	   return c;
	 }
}
