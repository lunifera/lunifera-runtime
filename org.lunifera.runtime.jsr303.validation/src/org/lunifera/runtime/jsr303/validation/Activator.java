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
package org.lunifera.runtime.jsr303.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.ValidationProviderResolver;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ValidationProvider;

import org.apache.bval.jsr303.ApacheValidationProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator, ValidationProviderResolver {

	private ServiceRegistration<ValidatorFactory> registry;

	@Override
	public void start(BundleContext context) throws Exception {
		// provide the bean validation factory
		ValidatorFactory avf = Validation
				.byProvider(ApacheValidationProvider.class)
				.providerResolver(this).configure().buildValidatorFactory();

		registry = context.registerService(ValidatorFactory.class, avf, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (registry != null) {
			registry.unregister();
			registry = null;
		}
	}

	@Override
	public List<ValidationProvider<?>> getValidationProviders() {
		List<ValidationProvider<?>> result = new ArrayList<ValidationProvider<?>>();
		result.add(new ApacheValidationProvider());
		return result;
	}
}
