/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;

/**
 * Provides common methods for inserting, retrieving, updating and deleting documents in Solr.
 */
public interface ISolrServerService {

	/**
	 * Adds a single document.
	 * 
	 * @param document The document to be inserted.
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse addDocument(SolrInputDocument document) 
			throws SolrServerException, IOException;
	
	/**
	 * Adds a collection of documents.
	 * 
	 * @param documents A collection of documents to be inserted.
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse addDocuments(Collection<SolrInputDocument> documents) 
			throws SolrServerException, IOException;
	
	/**
	 * Queries a document stored in Solr.
	 * 
	 * @param params
	 * @return UpdateResponse
	 * @throws SolrServerException
	 */
	QueryResponse query(SolrParams params)
            throws SolrServerException;
	
	/**
	 * Deletes all documents stored in this Solr instance.
	 * 
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse deleteAll()
            throws SolrServerException, IOException;
	
	/**
	 * Deletes a single document stored in Solr by using its ID.
	 * 
	 * @param id
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse deleteById(String id)
            throws SolrServerException,
                   IOException;
	
	/**
	 * Deletes documents stored in Solr by using their IDs. 
	 * 
	 * @param ids
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse deleteByIds(List<String> ids)
              throws SolrServerException, IOException;
	
	/**
	 * Deletes a documents stored in Solr by using a query. 
	 * 
	 * @param query
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse deleteByQuery(String query)
            throws SolrServerException, IOException;
	
	/**
	 * Commits all pending changes.
	 * 
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse commit()
            throws SolrServerException,
                   IOException;
	
	/**
	 * Roll back all changes since the last commit.
	 * 
	 * @return UpdateResponse
	 * @throws SolrServerException
	 * @throws IOException
	 */
	UpdateResponse rollback()
            throws SolrServerException,
                   IOException;
}
