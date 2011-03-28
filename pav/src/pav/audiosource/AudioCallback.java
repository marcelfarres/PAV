
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

package pav.audiosource;

/**
 * Audio Callback used by audio sources.
 * 
 * @author christopher
 */
public interface AudioCallback
{
	/**
	 * Called on new audio frames.
	 * 
	 * @param frame The frame. Must not be null
	 */
	void onNewFrame(float[] frame);
	
	/**
	 * Called on song changes.
	 */
	void onSongChanged();
	
	/**
	 * Called on sample rate changes.
	 * 
	 * @param sampleRate The new sample rate.
	 */
	void onSampleRateChanged(int sampleRate);
	
	/**
	 * Called on status changes.
	 * 
	 * @param status Status info
	 */
	void onStatusChanged(String[] status);
	
	/**
	 * Called on errors.
	 * 
	 * @param error The error
	 */
	void onError(Throwable error);
}
