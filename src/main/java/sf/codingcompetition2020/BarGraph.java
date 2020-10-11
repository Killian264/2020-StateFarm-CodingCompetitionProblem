package sf.codingcompetition2020;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BarGraph extends JPanel {
	private ArrayList<Bar> bars = new ArrayList<Bar>();
//	private ArrayList<Key> keys = new ArrayList<Key>();

	private int width;
	private int height;

	private int chartWidth;
	private int chartHeight;

	public BarGraph(int width, int height) {
		this.height = height;
		this.width = width;

//		this.chartHeight = (int) (height * .7);
//		this.chartWidth = (int) (width * .7);
//
//		JPanel dataTypePanel = new JPanel();
//		dataTypePanel.setLayout(new GridLayout(1, 2));
//		dataTypePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		dataTypePanel.add(new JLabel("Select Data Type"));
	}

//	public BarGraph(int width, int height, int chartWidth, int chartHeight) {
//		this.height = height;
//		this.width = width;
//
//		this.chartWidth = chartWidth;
//		this.chartHeight = chartHeight;
//	}

	public void addBar(Color color, int size) {
		Bar bar = new Bar(color, size);
		this.bars.add(bar);
		repaint();
	}

//	public void addKey(Color color, String description) {
//		Key key = new Key(color, description);
//		this.keys.add(key);
//		repaint();
//	}

	@Override
	protected void paintComponent(Graphics g) {
		int max = Integer.MIN_VALUE;
		for (Bar bar : bars) {
			if (bar.size > max) {
				max = bar.size;
			}
		}

		// Draw the actual bars
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (Bar bar : bars) {
			Color color = bar.color;
			int height = (int) ((getHeight() - 5) * ((double) bar.size / max));
			g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.height, this.width);
	}
}

class Bar {
	public Color color;
	public Integer size;

	public Bar(Color color, Integer size) {
		this.color = color;
		this.size = size;
	}
}

//class Key {
//	public Color color;
//	public String description;
//
//	public Key(Color color, String description) {
//		this.color = color;
//		this.description = description;
//	}
//}