package actoj.gui;

import java.awt.Font;

public abstract class DrawingBackend {

	protected float linewidth = 1f;
	protected float[] linedashpattern = new float[] {1};
	protected int linecolor = 0;
	protected int fillcolor = 0;
	protected float offsX = 0;
	protected float offsY = 0;
	protected float factorX = 1f;
	protected float factorY = 1f;
	protected Font font = new Font("Helvetica", Font.PLAIN, 12);

	public void setLineWidth(float linewidth) {
		this.linewidth = linewidth;
	}

	public float getLineWidth() {
		return linewidth;
	}

	protected void setLineDashPattern(float[] pattern) {
		this.linedashpattern = pattern;
	}

	public void setLineColor(int linecolor) {
		this.linecolor = linecolor;
	}

	public void setLineColor(int r, int g, int b, int a) {
		setLineColor(new java.awt.Color(r, g, b, a).getRGB());
	}

	public float getLineColor() {
		return linecolor;
	}

	public void setFillColor(int fillcolor) {
		this.fillcolor = fillcolor;
	}

	public void setFillColor(int r, int g, int b, int a) {
		setFillColor(new java.awt.Color(r, g, b, a).getRGB());
	}

	public float getFillColor() {
		return fillcolor;
	}

	public void setFont(Font f) {
		this.font = f;
	}

	public Font getFont() {
		return font;
	}

	public void setOffsX(float offsX) {
		this.offsX = offsX;
	}

	public float getOffsX() {
		return offsX;
	}

	public void setOffsY(float offsY) {
		this.offsY = offsY;
	}

	public float getOffsY() {
		return offsY;
	}

	public void setFactorX(float factorX) {
		this.factorX = factorX;
	}

	public float getFactorX() {
		return factorX;
	}

	public void setFactorY(float factorY) {
		this.factorY = factorY;
	}

	public float getFactorY() {
		return factorY;
	}

	public abstract void moveTo(float x, float y);

	public abstract void lineTo(float x, float y);

	public abstract void drawRectangle(float w, float h);

	public abstract void fillRectangle(float w, float h);

	public abstract void drawTriangle(float x0, float y0, float x1, float y1, float x2, float y2);

	public abstract void fillTriangle(float x0, float y0, float x1, float y1, float x2, float y2);

	public abstract void drawOval(float w, float h);

	public abstract void fillOval(float w, float h);

	public abstract void drawText(String text);

	public abstract void clip(float x, float y, float w, float h);

	public abstract void resetClip();
}
