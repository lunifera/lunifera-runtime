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
package org.lunifera.runtime.common.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation adds a constraint on a relation. Only references to objects
 * are allowed, that match the constraint.
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Constraint {

	/**
	 * The property of the target type of the reference that must match the enum
	 * literal.
	 */
	TargetEnumConstraint[] constraints();

}
 