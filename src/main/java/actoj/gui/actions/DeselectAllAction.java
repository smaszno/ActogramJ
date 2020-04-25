package actoj.gui.actions;

import actoj.ActogramJ_;
import actoj.gui.ImageCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DeselectAllAction extends AbstractAction {
	// 	ACCELERATOR_KEY
	// 	ACTION_COMMAND_KEY
	// 	DEFAULT
	// 	LONG_DESCRIPTION
	// 	MNEMONIC_KEY
	// 	NAME
	// 	SHORT_DESCRIPTION
	// 	SMALL_ICON

	private final ImageCanvas canvas;

	public DeselectAllAction(ImageCanvas canvas) {
		this.canvas = canvas;
		putValue(SHORT_DESCRIPTION, "Deselect all");
		putValue(LONG_DESCRIPTION, "Deselect all");
		putValue(NAME, "Deselect All tool");
		putValue(SMALL_ICON, new ImageIcon(
			ActogramJ_.class.getResource("icons/DeselectAll.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.deselectAll();
	}
}

