package actoj.gui;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import actoj.gui.actions.*;

@SuppressWarnings("serial")
public class ActoMenuBar extends JMenuBar implements ModeChangeListener {

	private JCheckBoxMenuItem pointer, calibration, selection;

	private ActogramCanvas.Mode mode = null;

	public ActoMenuBar(CustomWindow win) {
		super();

		JMenu menu = new JMenu("File");
		menu.add(new OpenAction(win.tree));
		menu.add(new ExportPDF(win.canvas));
		add(menu);


		ButtonGroup bg = new ButtonGroup();
		menu = new JMenu("Edit");
		pointer = new JCheckBoxMenuItem(new PointerAction(win.canvas));
		menu.add(pointer);
		bg.add(pointer);
		pointer.setSelected(true);
		this.mode = ActogramCanvas.Mode.POINTING;
		selection = new JCheckBoxMenuItem(new SelectingAction(win.canvas));
		menu.add(selection);
		bg.add(selection);
		calibration = new JCheckBoxMenuItem(new CalibAction(win.canvas));
		menu.add(calibration);
		bg.add(calibration);
		menu.addSeparator();
		menu.add(new PropertiesAction(win));
		add(menu);

		menu = new JMenu("Selection");
		menu.add(new SelectAllAction(win.canvas));
		menu.add(new DeselectAllAction(win.canvas));
		add(menu);

		menu = new JMenu("Analyze");
		menu.add(new CalculateAction(win));
		menu.add(new NormalizeAction(win));
		menu.addSeparator();
		menu.add(new FittingAction(win.canvas));
		menu.add(new AcrophaseAction(win.canvas));
		menu.add(new OnOffAction(win.canvas));
		menu.addSeparator();
		menu.add(new AverageActivityAction(win.canvas));
		menu.add(new AverageActivityOnsetOffsetSaveAction(win.canvas));
		menu.add(new AverageActivityOnsetOffsetClearAction(win.canvas));
		add(menu);

		menu = new JMenu("Help");
		menu.add(new HelpAction());
		add(menu);
	}

	public ActogramCanvas.Mode getMode() {
		return mode;
	}

	@Override
	public void modeChanged(ActogramCanvas.Mode mode) {
		JCheckBoxMenuItem b = null;
		switch(mode) {
			case POINTING: b = pointer; break;
			case FREERUNNING_PERIOD: b = calibration; break;
			case SELECTING: b = selection; break;
		}
		b.setSelected(true);
		this.mode = mode;
	}
}

