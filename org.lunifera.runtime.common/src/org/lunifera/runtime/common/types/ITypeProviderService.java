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
 * This service is responsible to load classes which are visible for the domain.
 * For instance jpa entities, dtos,...
 * <p>
 * Different concepts may be used. The default way to provide an implementation
 * is the use of the BundleSpace. A bundleSpace is a selected quantity of
 * bundles, that expose their classes. Bundles with the bundle header
 * <code>"Lun-RuntimeBuilder-BundleSpace"</code> will be automatically added
 * using the extender pattern. The default implementation of this service will
 * use the bundle space and tries to load classes by the registered bundles.
 */
public interface ITypeProviderService {

	/**
	 * Returns the class for the given qualified name and type. For emf pass the
	 * EClass as clazz.
	 * 
	 * @param qualifiedName
	 * @return
	 */
	Class<?> forName(Object clazz, String qualifiedName);

}
