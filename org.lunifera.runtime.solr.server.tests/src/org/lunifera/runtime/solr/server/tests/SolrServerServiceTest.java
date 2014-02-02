/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
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
		
		SolrInputDocument dtoDoc = new SolrInputDocument();
		dtoDoc.addField("uri", "doc://dto://org.lunifera.sample.dto.MyDTODoc");
		dtoDoc.addField("doc_version", 1.0);
		dtoDoc.addField("raw_content", "test dto content");
		
		SolrInputDocument entityDoc = new SolrInputDocument();
		entityDoc.addField("uri", "doc://entity://org.lunifera.sample.entity.MyEntityDoc");
		entityDoc.addField("doc_version", 0.8);
		entityDoc.addField("raw_content", "test entity content");
		
		SolrInputDocument viewDoc = new SolrInputDocument();
		viewDoc.addField("uri", "doc://view://org.lunifera.sample.view.MyViewDoc");
		viewDoc.addField("doc_version", 1.2);
		viewDoc.addField("raw_content", "test view content");
		
		SolrInputDocument uiDoc = new SolrInputDocument();
		uiDoc.addField("uri", "doc://ui://org.lunifera.sample.ui.MyUIDoc");
		uiDoc.addField("doc_version", 1.5);
		uiDoc.addField("raw_content", "test ui content");
		
		List<SolrInputDocument> documents = new ArrayList<>();
		documents.add(dtoDoc);
		documents.add(entityDoc);
		documents.add(viewDoc);
		documents.add(uiDoc);
		
		SolrQuery queryAll = new SolrQuery("*:*");
		try {
			// make sure the index is empty
			solrServerService.deleteAll();
			solrServerService.commit();
			QueryResponse response = solrServerService.query(queryAll);
			if(response == null || !response.getResults().isEmpty()) {
				throw new RuntimeException("Index should be empty after deleting all documents.");
			}
			response = null;
			
			// insert a single document
			solrServerService.addDocument(dtoDoc);
			solrServerService.commit();
			response = solrServerService.query(queryAll);
			if(response == null || response.getResults().getNumFound() != 1l) {
				throw new RuntimeException("After inserting one document into an empty index, "
						+ "the index should contain exactly one document.");
			}
			response = null;
			
			// empty index again
			solrServerService.deleteAll();
			solrServerService.commit();
			response = solrServerService.query(queryAll);
			if(response == null || !response.getResults().isEmpty()) {
				throw new RuntimeException("Index should be empty after deleting all documents.");
			}
			response = null;
			
			// add a collection of documents
			solrServerService.addDocuments(documents);
			solrServerService.commit();
			response = solrServerService.query(queryAll);
			if(response == null || response.getResults().getNumFound() != 4l) {
				throw new RuntimeException("After inserting a collection of four documents "
						+ "into an empty index, the index should contain exactly four documents.");
			}
			response = null;
			
			// query a specific document
			response = solrServerService.query(new SolrQuery("doc_version:1.0"));
			if(response == null || response.getResults().getNumFound() != 1l) {
				throw new RuntimeException("Query for doc_version 1.0 should return "
						+ "exactly 1 document.");
			}
			SolrDocumentList resultDocs = response.getResults();
			if(!resultDocs.get(0).get("uri").toString().
					equals("doc://dto://org.lunifera.sample.dto.MyDTODoc")) {
				throw new RuntimeException("Query for doc_version 1.0 should myDTODoc.");
			}
			response = null;
			
			// delete a specific document by query
			solrServerService.deleteByQuery("doc_version:1.0");
			solrServerService.commit();
			response = solrServerService.query(new SolrQuery("doc_version:1.0"));
			if(response == null || !response.getResults().isEmpty()) {
				throw new RuntimeException("MyDTODoc should have been deleted by "
						+ "using query doc_version:1.0");
			}
			response = null;
			
			// delete a specific document by id
			UpdateResponse updateResponse = solrServerService.deleteById("doc://entity://org.lunifera.sample.entity.MyEntityDoc");
			solrServerService.commit();
			response = solrServerService.query(
					new SolrQuery("uri:doc\\://entity\\://org.lunifera.sample.entity.MyEntityDoc"));
			if(response == null || !response.getResults().isEmpty()) {
				throw new RuntimeException("MyEntityDoc should have been deleted by "
						+ "using its id");
			}
			response = null;
			
			// delete several documents by id
			List<String> idsToDelete = new ArrayList<>();
			idsToDelete.add("doc://view://org.lunifera.sample.view.MyViewDoc");
			idsToDelete.add("doc://ui://org.lunifera.sample.ui.MyUIDoc");
			solrServerService.deleteByIds(idsToDelete);
			solrServerService.commit();
			response = solrServerService.query(
					new SolrQuery("uri:doc\\://view\\://org.lunifera.sample.view.MyViewDoc"
							+ "OR uri:doc\\://ui\\://org.lunifera.sample.ui.MyUIDoc"));
			if(response == null || !response.getResults().isEmpty()) {
				throw new RuntimeException("MyViewDoc and MyUIDoc should have been deleted by "
						+ "using their ids");
			}
			response = null;
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
