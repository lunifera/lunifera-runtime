/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server;

public enum LuniferaDocFields {

	URI("uri"),
	DOC_VERSION("doc_version"),
	RAW_CONTENT("raw_content");
	
	private final String fName;
	
	private LuniferaDocFields(String fName) {
		this.fName = fName;
	}

	public String fieldName() {
		return fName;
	}
	
}
