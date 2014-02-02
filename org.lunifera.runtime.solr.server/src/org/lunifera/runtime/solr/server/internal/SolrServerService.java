/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.internal;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.CoreDescriptor;
import org.apache.solr.core.SolrCore;
import org.lunifera.runtime.solr.server.ISolrServerService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

public class SolrServerService implements ISolrServerService {

	private static final String NAME_PROPERTY = "name";
	private static final String INSTANCEDIR_PROPERTY = "instanceDir";

	private LogService logService;

	private CoreContainer coreContainer;
	private EmbeddedSolrServer server;

	private String name;
	private String instanceDir;

	protected void activate(ComponentContext context) {
		logService.log(LogService.LOG_DEBUG, "activated");
		
		// TODO these params should be configured via CM
		if (context.getProperties().get(NAME_PROPERTY) != null) { 
			name = context.getProperties().get(NAME_PROPERTY).toString();
		}
		if (context.getProperties().get(INSTANCEDIR_PROPERTY) != null) {
			instanceDir = context.getProperties().get(INSTANCEDIR_PROPERTY).toString();
		}
		
		server = initialize(name, instanceDir);
	}

	protected void deactivate(ComponentContext context) {
		logService.log(LogService.LOG_DEBUG, "deactivated");
		if (server != null) {
			server.shutdown();
		}
	}

	protected void modified(ComponentContext context) {
		logService.log(LogService.LOG_DEBUG, "modified");
		
		name = context.getProperties().get(NAME_PROPERTY).toString();
		instanceDir = context.getProperties().get(INSTANCEDIR_PROPERTY).toString();
		
		if (server != null) {
			server.shutdown();
		}
		server = initialize(name, instanceDir);
	}

	protected synchronized void bindCoreContainer(CoreContainer coreContainer) {
		this.coreContainer = coreContainer;
	}

	protected synchronized void unbindCoreContainer(CoreContainer coreContainer) {
		this.coreContainer = null;
	}

	protected synchronized void bindLogService(LogService logService) {
		this.logService = logService;
	}

	protected synchronized void unbindLogService(LogService logService) {
		this.logService = null;
	}

	@Override
	public UpdateResponse addDocument(SolrInputDocument document) throws SolrServerException,
			IOException {
		return server.add(document);
	}
	
	@Override
	public UpdateResponse addDocuments(Collection<SolrInputDocument> documents)
			throws SolrServerException, IOException {
		return server.add(documents);
	}

	@Override
	public QueryResponse query(SolrParams params) throws SolrServerException {
		return server.query(params);
	}

	@Override
	public UpdateResponse deleteAll() throws SolrServerException, IOException {
		return server.deleteByQuery("*:*");
	}

	@Override
	public UpdateResponse deleteById(String id) throws SolrServerException, IOException {
		return server.deleteById(id);
	}

	@Override
	public UpdateResponse deleteByIds(List<String> ids) throws SolrServerException, IOException {
		return server.deleteById(ids);
	}

	@Override
	public UpdateResponse deleteByQuery(String query) throws SolrServerException, IOException {
		return server.deleteByQuery(query);
	}
	
	@Override
	public UpdateResponse commit() throws SolrServerException, IOException {
		return server.commit();
	}
	
	@Override
	public UpdateResponse rollback() throws SolrServerException, IOException {
		return server.rollback();
	}

	private EmbeddedSolrServer initialize(String name, String instanceDir) {
		if (name == null || instanceDir == null) {
			throw new RuntimeException("Core name and instance dir must be configured.");
		}

		CoreDescriptor coreDescriptor = new CoreDescriptor(coreContainer, name, instanceDir);
		SolrCore solrCore = coreContainer.create(coreDescriptor);
		coreContainer.register(solrCore, false);
		return new EmbeddedSolrServer(coreContainer, name);
	}

}
