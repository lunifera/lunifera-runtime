/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.lunifera.runtime.solr.server.ISolrServerService;

/**
 * Tests for SolrServerService
 */
@RunWith(JUnit4.class)
public class SolrServerServiceTest {

	private static final String URI = "uri";
	private static final String DOC_VERSION = "doc_version";
	private static final String RAW_CONTENT = "raw_content";
	
	private static final String QUERY_ALL = "*:*";

	private static CountDownLatch dependencyLatch = new CountDownLatch(1);
	private static ISolrServerService solrServerService;
	
	private static SolrInputDocument dtoDoc;
	private static SolrInputDocument entityDoc;
	private static SolrInputDocument viewDoc;
	private static SolrInputDocument uiDoc;
	private static List<SolrInputDocument> documents;
	private static List<SolrInputDocument> loadTestDocs;
	
	@BeforeClass
	public static void setUpTestData() {
		dtoDoc = new SolrInputDocument();
		dtoDoc.addField(URI, "doc://dto://org.lunifera.sample.dto.MyDTODoc");
		dtoDoc.addField(DOC_VERSION, 1.0);
		dtoDoc.addField(RAW_CONTENT, "test dto content");

		entityDoc = new SolrInputDocument();
		entityDoc.addField(URI, "doc://entity://org.lunifera.sample.entity.MyEntityDoc");
		entityDoc.addField(DOC_VERSION, 0.8);
		entityDoc.addField(RAW_CONTENT, "test entity content");

		viewDoc = new SolrInputDocument();
		viewDoc.addField(URI, "doc://view://org.lunifera.sample.view.MyViewDoc");
		viewDoc.addField(DOC_VERSION, 1.2);
		viewDoc.addField(RAW_CONTENT, "test view content");

		uiDoc = new SolrInputDocument();
		uiDoc.addField(URI, "doc://ui://org.lunifera.sample.ui.MyUIDoc");
		uiDoc.addField(DOC_VERSION, 1.5);
		uiDoc.addField(RAW_CONTENT, "test ui content");

		documents = new ArrayList<>();
		documents.add(dtoDoc);
		documents.add(entityDoc);
		documents.add(viewDoc);
		documents.add(uiDoc);
		
		loadTestDocs = new ArrayList<>();
		for (int i = 1; i <= 500; i++) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField(URI, "uri_" + i);
			doc.addField(DOC_VERSION, 1.0);
			doc.addField(RAW_CONTENT, "content_" + i);
			loadTestDocs.add(doc);
		}
	}

	@Before
	public void dependencyCheck() {
		try {
			dependencyLatch.await(5, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			fail("Referenced service SolrServerService not bound.");
		}
	}

	@Before
	public void deleteAll() {
		try {
			solrServerService.deleteAll();
			solrServerService.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testInsertSingleDocument() throws SolrServerException, IOException {
		solrServerService.addDocument(dtoDoc);
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery(QUERY_ALL));
		assertNotNull(response);
		assertEquals(1l, response.getResults().getNumFound());
	}
	
	@Test
	public void testDeleteAll() throws SolrServerException, IOException {
		solrServerService.addDocument(dtoDoc);
		solrServerService.commit();
		
		solrServerService.deleteAll();
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery(QUERY_ALL));
		assertNotNull(response);
		assertTrue(response.getResults().isEmpty());
	}
	
	@Test
	public void testInsertMultipleDocuments() throws SolrServerException, IOException {
		solrServerService.addDocuments(documents);
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery(QUERY_ALL));
		assertNotNull(response);
		assertEquals(3l, response.getResults().getNumFound());
	}
	
	@Test
	public void testQueryDocument() throws SolrServerException, IOException {
		solrServerService.addDocuments(documents);
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery("doc_version:1.0"));
		assertNotNull(response);
		assertEquals(1l, response.getResults().getNumFound());
		assertEquals("doc://dto://org.lunifera.sample.dto.MyDTODoc", 
				response.getResults().get(0).get("uri").toString());
	}
	
	@Test
	public void testDeleteByQuery() throws SolrServerException, IOException {
		solrServerService.addDocuments(documents);
		solrServerService.commit();
		
		solrServerService.deleteByQuery("doc_version:1.0");
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery("doc_version:1.0"));
		assertNotNull(response);
		assertTrue(response.getResults().isEmpty());
	}
	
	@Test
	public void testDeleteByID() throws SolrServerException, IOException {
		solrServerService.addDocuments(documents);
		solrServerService.commit();
		
		solrServerService.deleteById("doc://entity://org.lunifera.sample.entity.MyEntityDoc");
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery(
				"uri:doc\\://entity\\://org.lunifera.sample.entity.MyEntityDoc"));
		assertNotNull(response);
		assertTrue(response.getResults().isEmpty());
	}
	
	@Test
	public void testDeleteByIDs() throws SolrServerException, IOException {
		solrServerService.addDocuments(documents);
		solrServerService.commit();
		
		List<String> idsToDelete = new ArrayList<>();
		idsToDelete.add("doc://view://org.lunifera.sample.view.MyViewDoc");
		idsToDelete.add("doc://ui://org.lunifera.sample.ui.MyUIDoc");
		solrServerService.deleteByIds(idsToDelete);
		solrServerService.commit();
		
		QueryResponse response = solrServerService.query(new SolrQuery(
				"uri:doc\\://view\\://org.lunifera.sample.view.MyViewDoc" + 
				"OR uri:doc\\://ui\\://org.lunifera.sample.ui.MyUIDoc"));
		assertNotNull(response);
		assertTrue(response.getResults().isEmpty());
	}
	
	@Test
	public void testBulkInsert() throws SolrServerException, IOException {
		final long beforeInsertMs = System.currentTimeMillis();
		solrServerService.addDocuments(loadTestDocs);
		solrServerService.commit();
		final long afterInsertMs = System.currentTimeMillis();
		final long insertDuration = afterInsertMs - beforeInsertMs;
		System.out.println("Inserting 500 documents took " + insertDuration + "ms.");
	}
	
	protected void bindSolrServerService(ISolrServerService service) {
		solrServerService = service;
		dependencyLatch.countDown();
	}

	protected void unbindSolrServerService(ISolrServerService service) {
		solrServerService = null;
	}

}
