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
package org.lunifera.runtime.common.keystroke;

import java.util.Arrays;

public class KeyStrokeDefinition {

	private final String caption;
	private final int keyCode;
	private final int[] modifierKeys;

	public KeyStrokeDefinition(String caption, int keyCode, int[] modifierKeys) {
		super();
		this.caption = caption;
		this.keyCode = keyCode;
		this.modifierKeys = modifierKeys != null ? Arrays.copyOf(modifierKeys,
				modifierKeys.length) : new int[0];
	}

	/**
	 * Returns the caption of the keystroke definition. Later it may use special
	 * characters to define keystrokes like <code>^&R</code> which may define
	 * CRTL+ALT+R. But this feature is not supported yet.
	 * 
	 * @return
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Returns the key code. See See {@link KeyCode}.
	 * 
	 * @return
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * Returns the modifier keys. See See {@link ModifierKey}.
	 * 
	 * @return
	 */
	public int[] getModifierKeys() {
		return Arrays.copyOf(modifierKeys, modifierKeys.length);
	}

}
