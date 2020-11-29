package actoj.gui.actions;

import actoj.ActogramJ_;
import actoj.gui.ImageCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AverageActivityOnsetOffsetClearAction extends AbstractAction {
	// 	ACCELERATOR_KEY
	// 	ACTION_COMMAND_KEY
	// 	DEFAULT
	// 	LONG_DESCRIPTION
	// 	MNEMONIC_KEY
	// 	NAME
	// 	SHORT_DESCRIPTION
	// 	SMALL_ICON

	private final ImageCanvas canvas;

	public AverageActivityOnsetOffsetClearAction(ImageCanvas canvas) {
		this.canvas = canvas;
		putValue(SHORT_DESCRIPTION, "Average Activity Onset Offset Point Clear");
		putValue(LONG_DESCRIPTION, "Average Activity Onset Offset Point Clear");
		putValue(NAME, "Avg Act On Off Clear");
		putValue(SMALL_ICON, new ImageIcon(
			ActogramJ_.class.getResource("icons/AverageActivityOnsetOffsetClear.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.clearAverageActivityOnsetOffset();
	}
}

