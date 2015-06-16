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
package org.lunifera.runtime.common.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.lunifera.runtime.common.state.ISharedStateContext;

/**
 * This annotation marks fields in DTOs, that it is a dirty-indicator boolean
 * field. If the annotation is present for a DTO, then it is automatically
 * managed by the {@link ISharedStateContext}.
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface Dirty {

}
