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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks fields in JPA entities and DTOs, that the content is a
 * human readable domain description. For instance the description of a product.<br>
 * Since descriptions may be based on many field values, this annotation can
 * also be used for getter-methods. If a getter is annotated with
 * 
 * @DomainDescription, the getter is used to access the description for that
 *                     instance.
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface DomainDescription {

}
 