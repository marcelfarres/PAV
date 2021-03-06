
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

package pav.configurator;

import pav.lib.visualizer.Visualizer;

/**
 * Allows runtime configuration of a visualizer via a query entered by the user.
 */
public interface Configurator
{
	/**
	 * Processes a configuration request.
	 * 
	 * @param subject The visualizer to configure. Must not be null
	 * @param query The user query. Must not be null
	 * @return Whether this configurator was able to handle the configuration request
	 */
	boolean process(Visualizer subject, String query);
}
