package actoj.gui;

import javax.swing.*;

import ij.IJ;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

public class StatusBar extends JPanel {

	private final CustomWindow win;

	private final JLabel posLabel;
	private final JLabel periodLabel = new JLabel("Freerunning period:                    ");

	public StatusBar(CustomWindow window) {
		super();
		this.win = window;
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

		add(new JLabel("Zoom: "));
		final JComboBox zoomBox = new JComboBox(getZoomlevelStrings());
		zoomBox.setSelectedIndex(Zoom.DEFAULT_ZOOM);
		zoomBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					win.canvas.getZoom().zoom(zoomBox.getSelectedIndex());
				} catch(Error ex) {
					IJ.error(ex.getClass() + ": " + ex.getMessage());
					ex.printStackTrace();
				} catch(Exception ex) {
					IJ.error(ex.getClass() + ": " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		add(zoomBox);

		add(new JLabel("Position: "));

		posLabel = new JLabel("") {
			private JLabel l = new JLabel("mmmmmmmmmmmmmmmm");
			public Dimension getPreferredSize() {
				return l.getPreferredSize();
			}
		};
		add(posLabel);
		add(periodLabel);
	}

	public void setPositionString(String pos) {
		posLabel.setText(pos);
	}

	public void setFreerunningPeriod(String p) {
		periodLabel.setText("Freerunning period: " + p);
	}

	private static String[] getZoomlevelStrings() {
		int l = Zoom.LEVELS.length;
		String[] s = new String[l];
		for(int i = 0; i < l; i++)
			s[i] = "1 : " + Zoom.LEVELS[i];
		return s;
	}
}

