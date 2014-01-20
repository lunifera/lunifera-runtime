/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.internal;

import org.apache.solr.core.CoreContainer;
import org.lunifera.runtime.solr.server.ILuniferaEmbeddedSolrServer;
import org.lunifera.runtime.solr.server.ISolrServerService;
import org.osgi.service.component.ComponentContext;

public class SolrServerService implements ISolrServerService {

	private CoreContainer coreContainer;
	private ILuniferaEmbeddedSolrServer luniferaEmbeddedSolrServer;

	public void activate(ComponentContext ctx) {
	}
	
	public void deactivate(ComponentContext ctx) {
	}
	
	protected synchronized void bindCoreContainer(CoreContainer coreContainer) {
		this.coreContainer = coreContainer;
	}

	protected synchronized void unbindCoreContainer(CoreContainer coreContainer) {
		this.coreContainer = null;
	}

	@Override
	public ILuniferaEmbeddedSolrServer getEmbeddedSolrServer() {
		return null;
	}

}
