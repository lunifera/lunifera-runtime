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
package org.lunifera.runtime.typeprovider.bundlespace;

import org.lunifera.runtime.common.types.IBundleSpace;
import org.lunifera.runtime.common.types.ITypeProviderService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true)
public class BundleSpaceTypeProviderService implements ITypeProviderService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BundleSpaceTypeProviderService.class);

	private IBundleSpace bundleSpace;

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	protected void bindBundleSpace(IBundleSpace bundleSpace) {
		this.bundleSpace = bundleSpace;
	}

	protected void unbindBundleSpace(IBundleSpace bundleSpace) {
		this.bundleSpace = null;
	}
	
	@Override
	public Class<?> forName(Object clazz, String qualifiedName) {
		try {
			return bundleSpace.forName(qualifiedName);
		} catch (ClassNotFoundException e) {
			LOGGER.warn("{}", e.getMessage());
		}

		return null;
	}

}
