package org.lunifera.runtime.common.hash;

import com.google.common.hash.Hashing;

/**
 * Common HashUtil
 */
public class HashUtil {

	/**
	 * Creates a hash code for an object with the class name and objects
	 * hashCode.
	 * <p>
	 * <i>org.lunifera.dtos.ItemDTO(id=17):</i>
	 * <code>hash("org.lunifera.dtos.ItemDto({object.hashCode()})")</code>
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static String createObjectWithIdHash(Class<?> clazz, Object object) {
		return createObjectWithIdHash(clazz.getCanonicalName(), object);
	}

	/**
	 * Creates a hash code for an object with the class name and objects
	 * hashCode.
	 * <p>
	 * <i>org.lunifera.dtos.ItemDTO(id=17):</i>
	 * <code>hash("org.lunifera.dtos.ItemDto({object.hashCode()})")</code>
	 * 
	 * @param objectName
	 * @param id
	 * @return
	 */
	public static String createObjectWithIdHash(String objectName, Object object) {
		return Hashing
				.sha1()
				.hashBytes(
						String.format("%s(%s)", objectName,
								String.valueOf(object.hashCode())).getBytes())
				.toString();
	}

}
