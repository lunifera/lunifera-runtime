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
import org.osgi.service.log.LogService;

public class SolrServerService implements ISolrServerService {

	private LogService logService;
	private ILuniferaEmbeddedSolrServer luniferaEmbeddedSolrServer;

	protected synchronized void bindCoreContainer(CoreContainer coreContainer) {
		// TODO these params should be configured via CM
		luniferaEmbeddedSolrServer = new LuniferaEmbeddedSolrServer(
				coreContainer, "Lunifera", "/path/to/your/solrconf");
	}

	protected synchronized void unbindCoreContainer(CoreContainer coreContainer) {
		luniferaEmbeddedSolrServer = null;
	}
	
	protected synchronized void bindLogService(LogService logService) {
		this.logService = logService;
	}

	protected synchronized void unbindLogService(LogService logService) {
		this.logService = null;
	}

	@Override
	public ILuniferaEmbeddedSolrServer getEmbeddedSolrServer() {
		logService.log(LogService.LOG_INFO, "Returning instance of LuniferaEmbeddedSolrServer.");
		return luniferaEmbeddedSolrServer;
	}

}
