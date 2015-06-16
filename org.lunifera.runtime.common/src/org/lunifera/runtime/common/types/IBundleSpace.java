/**
 * Copyright (c) 2011 - 2015, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package org.lunifera.runtime.common.types;

/**
 * A bundle space is a collection of bundles that expose their classes. Bundles
 * with the bundle header <code>"Lun-RuntimeBuilder-BundleSpace"</code> will be
 * automatically added using the extender pattern.
 */
public interface IBundleSpace {

	/**
	 * Tries to find a class for the given class name using the bundles
	 * registered in the bundle space.
	 * 
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
	Class<?> forName(String name) throws ClassNotFoundException;

}
