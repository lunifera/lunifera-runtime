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
