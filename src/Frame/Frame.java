package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.math.BigDecimal;

import java.util.List;

import javax.swing.JFrame;

import org.javatuples.Pair;

public class Frame extends JFrame {
	private List<Pair<Double, Double>> cords;
	
	public Frame(List<Pair<Double, Double>> cords) {
		this.cords = cords;

		setBackground(Color.LIGHT_GRAY);
		setSize(new Dimension(1000, 1000));
		setTitle("Polygon");
		setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g) {
		int[] xs = new int[cords.size()];
		int[] ys = new int[cords.size()];

		for (int i = 0; i < cords.size(); ++i) {
			BigDecimal x = new BigDecimal(cords.get(i).getValue0() * 10);
			BigDecimal y = new BigDecimal(cords.get(i).getValue1() * 10);
			xs[i] = (y.intValue() * -1) + 2000;
			ys[i] = x.intValue() + 1000;
			System.out.println(x.intValue() + " : " + y.intValue());
		}

		Polygon triangle = new Polygon(xs, ys, xs.length);
		g.setColor(Color.red);
		g.fillPolygon(triangle);
	}
}
