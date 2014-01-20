/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.internal;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.CoreDescriptor;
import org.apache.solr.core.SolrCore;
import org.lunifera.runtime.solr.server.ILuniferaEmbeddedSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuniferaEmbeddedSolrServer implements ILuniferaEmbeddedSolrServer {

	private final static Logger logger = LoggerFactory.getLogger(LuniferaEmbeddedSolrServer.class);
	
	private CoreContainer coreContainer;
	private String name;
	private String instanceDir;
	
	private EmbeddedSolrServer eSolrServer;
	
	public LuniferaEmbeddedSolrServer(CoreContainer coreContainer, String name, String instanceDir) {
		this.coreContainer = coreContainer;
		this.name = name;
		this.instanceDir = instanceDir;
		
		initialize();
	}
	
	private void initialize() {		
		if(name == null || instanceDir == null) {
			logger.error("Core name and instance dir must be configured.");
			return;
		}
		
		CoreDescriptor coreDescriptor = new CoreDescriptor(
				coreContainer, name, instanceDir);
		SolrCore solrCore = coreContainer.create(coreDescriptor);
		coreContainer.register(solrCore, false);
		eSolrServer = new EmbeddedSolrServer(coreContainer, name);
	}

	@Override
	public UpdateResponse addDocument(SolrInputDocument document) 
			throws SolrServerException, IOException {
		return eSolrServer.add(document);
	}
	
}
