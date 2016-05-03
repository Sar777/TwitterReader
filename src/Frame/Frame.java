package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import org.javatuples.Pair;
import Data.StateEntry;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	private HashSet<StateEntry> states;
	
	public Frame(HashSet<StateEntry> states) {
		this.states = states;

		setBackground(Color.LIGHT_GRAY);
		setSize(new Dimension(800, 800));
		setTitle("Polygon");
		setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g) {
		for (StateEntry stateEntry : states) {
		    Random randomGenerator = new Random();
		    int red = randomGenerator.nextInt(256);
		    int green = randomGenerator.nextInt(256);
		    int blue = randomGenerator.nextInt(256);

		    Color randomColour = new Color(red,green,blue);

			for (Map.Entry<Integer, List<Pair<Double, Double>>> entry : stateEntry.getCoordinates().entrySet()) {
			    List<Pair<Double, Double>> list = entry.getValue();
				int[] xs = new int[list.size()];
				int[] ys = new int[list.size()];
			    for (int i = 0; i < list.size(); ++i) {	
					
					BigDecimal x = new BigDecimal(list.get(i).getValue0() * 17);
					BigDecimal y = new BigDecimal(list.get(i).getValue1() * 17);
			
					xs[i] = (int) ((x.intValue() / 3)) + 1000;
					ys[i] = (int) ((y.intValue() / 3) * -1) + 600;
			    }

				g.setColor(randomColour);
				g.fillPolygon(new Polygon(xs, ys, xs.length));
			}
		}
	}
}
