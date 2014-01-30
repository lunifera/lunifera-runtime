/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.tests;

import java.io.IOException;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.lunifera.runtime.solr.server.ISolrServerService;

/**
 * Tests for SolrServerService
 */
// TODO JUnit-ify
public class SolrServerServiceTest {

	private ISolrServerService solrServerService;
	
	protected void bindSolrServerService(ISolrServerService solrServerService) {
		this.solrServerService = solrServerService;
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("uri", UUID.randomUUID().toString());
		doc.addField("doc_version", 1.0);
		doc.addField("raw_content", "test content");
		
		SolrQuery queryAll = new SolrQuery();
		queryAll.setQuery("*:*");
		try {
			solrServerService.deleteAll();
			solrServerService.commit();
			QueryResponse response = solrServerService.query(queryAll);
			if(!response.getResults().isEmpty()) {
				throw new RuntimeException("Index should be empty after deleting all documents.");
			}
			
			solrServerService.addDocument(doc);
			solrServerService.commit();
			
			response = solrServerService.query(queryAll);
			if(response.getResults().getNumFound() != 1l) {
				throw new RuntimeException("After inserting one document into an empty index, "
						+ "the index should contain exactly one document.");
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void unbindSolrServerService(ISolrServerService solrServerService) {
		this.solrServerService = null;
	}
	
}
