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
