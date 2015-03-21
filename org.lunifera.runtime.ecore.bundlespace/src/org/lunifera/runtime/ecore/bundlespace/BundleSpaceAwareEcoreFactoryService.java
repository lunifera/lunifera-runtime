package org.lunifera.runtime.ecore.bundlespace;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.lunifera.runtime.common.types.IBundleSpace;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class BundleSpaceAwareEcoreFactoryService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BundleSpaceAwareEcoreFactoryService.class);

	private IBundleSpace bundleSpace;

	private EFactory oldFactory;

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "unbindBundleSpace")
	protected void bindBundleSpace(IBundleSpace bundleSpace) {
		this.bundleSpace = bundleSpace;
	}

	protected void unbindBundleSpace(IBundleSpace bundleSpace) {
		this.bundleSpace = null;
	}

	@Activate
	protected void activate() {
		oldFactory = EcorePackage.eINSTANCE.getEFactoryInstance();
		// ensure that ecore will load classes based on bundlespace
		EcorePackage.eINSTANCE
				.setEFactoryInstance(new ClassLoadingDelegatingEcoreFactory());

		LOGGER.info("Bound BundleSpaceAware EcoreFactory.");
	}

	@Deactivate
	protected void deactivate() {
		EcorePackage.eINSTANCE.setEFactoryInstance(oldFactory);
		oldFactory = null;
	}

	private class ClassLoadingDelegatingEcoreFactory extends EcoreFactoryImpl {
		public Class<?> createEJavaClassFromString(EDataType metaObject,
				String initialValue) {
			try {
				if (initialValue == null)
					return null;
				else if ("boolean".equals(initialValue))
					return boolean.class;
				else if ("byte".equals(initialValue))
					return byte.class;
				else if ("char".equals(initialValue))
					return char.class;
				else if ("double".equals(initialValue))
					return double.class;
				else if ("float".equals(initialValue))
					return float.class;
				else if ("int".equals(initialValue))
					return int.class;
				else if ("long".equals(initialValue))
					return long.class;
				else if ("short".equals(initialValue))
					return short.class;
				else
					return bundleSpace.forName(initialValue);
			} catch (ClassNotFoundException e) {
				throw new WrappedException(e);
			}
		}
	}
}
