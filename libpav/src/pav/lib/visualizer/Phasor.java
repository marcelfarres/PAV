
/*
 * Processing Audio Visualization (PAV)
 * Copyright (C) 2011  Christopher Pramerdorfer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pav.lib.visualizer;

import pav.lib.PAVException;
import pav.lib.frame.Frame;
import processing.core.PApplet;

/**
 * Phasor draws the amplitude of the frame samples versus their rate of change (first derivate).
 * 
 * @author christopher
 */
public class Phasor extends VisualizerAbstract
{
	/**
	 * Draw dots.
	 */
	public static final int MODE_DOTS = 1;
	
	/**
	 * Draw a a shape (connect dots with lines).
	 */
	public static final int MODE_LINES = 2;
	
	/**
	 * Draw a shape (connect dots with curves).
	 * 
	 * This offers the best quality at the cost of speed.
	 */
	public static final int MODE_CURVES = 3;
	
	private static final long serialVersionUID = 4412875346457402076L;
	
	private int _mode;
	private float _strokeWeight;
	private transient float _vMin, _vMax, _dMin, _dMax;
	
	/**
	 * Ctor.
	 */
	public Phasor()
	{
		_vMin = Float.MAX_VALUE;
		_vMax = Float.MIN_VALUE;
		_dMin = Float.MAX_VALUE;
		_dMax = Float.MIN_VALUE;
		
		setMode(MODE_CURVES);
		setStrokeWeight(1);
		setColor(0xFFFF0000, 0xFFFFFF00, PApplet.RGB);
	}

	@Override
	public void process() throws PAVException
	{
		p.strokeWeight(_strokeWeight);
		
		float[] frame = Frame.samples();
		int len = frame.length;
		int len1 = len - 1;
		float[] frameDeriv = new float[len];
		float dMin = Float.MAX_VALUE;
		float dMax = Float.MIN_VALUE;
		
		for(int i = 1; i < len1; i++) {
			float v = frame[i + 1] - frame[i - 1];
			frameDeriv[i] = v;
			
			if(v > dMax) dMax = v;
			if(v < dMin) dMin = v;
		}
		
		float vMin = Float.MAX_VALUE;
		float vMax = Float.MIN_VALUE;
		
		for(int i = 0; i < len; i++) {
			float v = frame[i];
			
			if(v > vMax) vMax = v;
			if(v < vMin) vMin = v;
		}
		
		if(dMax > _dMax) _dMax = dMax;
		if(vMax > _vMax) _vMax = vMax;
		if(dMin < _dMin) _dMin = dMin;
		if(vMin < _vMin) _vMin = vMin;
		
		float[] area = getArea();
		float width2 = (area[2] - area[0]) / 2f;
		float height2 = (area[3] - area[1]) / 2f;
		
		cm.setRange(0, len - 1);

		if(_mode != MODE_DOTS) {
			p.noFill();
			p.beginShape();
		}
		
		for(int i = 1; i < len1; i++) {
			float x = frameDeriv[i];
			float y = frame[i];
			
			x = (x < 0) ? PApplet.map(x, _dMin, 0, - width2, 0) : PApplet.map(x, 0, _dMax, 0, width2);
			y = (y < 0) ? PApplet.map(y, _vMin, 0, height2, 0) : PApplet.map(y, 0, _vMax, 0, - height2);
			
			p.stroke(cm.map(i));
			
			if(_mode == MODE_LINES) {
				p.curveVertex(area[0] + width2 + x, area[1] + height2 + y);
			}
			else if(_mode == MODE_CURVES) {
				p.curveVertex(area[0] + width2 + x, area[1] + height2 + y);
			}
			else {
				p.point(area[0] + width2 + x, area[1] + height2 + y);
			}
		}
		
		if(_mode != MODE_DOTS) {
			p.endShape();
		}
	}
	
	/**
	 * Sets the visualization mode. Must be a valid mode according
	 * to the MODE_ constants of this class.
	 * 
	 * @param mode The visualization mode
	 */
	public void setMode(int mode)
	{
		_mode = mode;
	}
	
	/**
	 * Sets the stroke weight to use when drawing.
	 * 
	 * @param weight The stroke weight. Must be > 0
	 */
	public void setStrokeWeight(float weight)
	{
		_strokeWeight = weight;
	}
	
	@Override
	public String toString()
	{
		switch(_mode) {
			case MODE_DOTS :
				return "Phasor (dot mode)";
			case MODE_LINES :
				return "Phasor (line mode)";
			case MODE_CURVES :
				return "Phasor (curve mode)";
			default :
				return "Phasor";
		}
	}
	
	@Override
	public void dispose() { }
}
