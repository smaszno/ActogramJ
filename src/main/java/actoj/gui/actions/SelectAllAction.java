package actoj.gui.actions;

import actoj.ActogramJ_;
import actoj.gui.ActogramCanvas;
import actoj.gui.ImageCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SelectAllAction extends AbstractAction {
	// 	ACCELERATOR_KEY
	// 	ACTION_COMMAND_KEY
	// 	DEFAULT
	// 	LONG_DESCRIPTION
	// 	MNEMONIC_KEY
	// 	NAME
	// 	SHORT_DESCRIPTION
	// 	SMALL_ICON

	private final ImageCanvas canvas;

	public SelectAllAction(ImageCanvas canvas) {
		this.canvas = canvas;
		putValue(SHORT_DESCRIPTION, "Select all");
		putValue(LONG_DESCRIPTION, "Select all");
		putValue(NAME, "Select All tool");
		putValue(SMALL_ICON, new ImageIcon(
			ActogramJ_.class.getResource("icons/SelectAll.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.selectAll();
	}
}

