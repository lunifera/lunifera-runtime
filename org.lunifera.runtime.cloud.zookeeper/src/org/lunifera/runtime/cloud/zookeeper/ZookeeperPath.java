/**
 * Copyright (c) 2012 Committers of lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 */
package org.lunifera.runtime.cloud.zookeeper;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Path used to access information in zookeeper.
 */
public class ZookeeperPath {

	/**
	 * The lunifera root path in zookeeper file system.
	 */
	public static final IPath PATH_ROOT = new Path("lunifera").makeAbsolute();

	/**
	 * The path containing all preferences for the root.
	 */
	public static final IPath PATH_PREFERENCES = PATH_ROOT
			.append("preferences").makeAbsolute();

	/**
	 * The path to access information about all cloud nodes.
	 */
	public static final IPath PATH_NODES = PATH_ROOT.append("nodes")
			.makeAbsolute();
}
