/*******************************************************************************
 * Copyright (c) 2013 Loetz KG (Heidelberg), Petra Bierleutgeb and others. All
 * rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.lunifera.runtime.solr.server;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 * Interface for exposing org.apache.solr.client.solrj.embedded.EmbeddedSolrServer
 */
public interface ILuniferaEmbeddedSolrServer {

	/**
	 * Adds a single document.
	 * 
	 * @param document The document to be inserted.
	 * @return UpdateResponse
	 */
	UpdateResponse addDocument(SolrInputDocument document) 
			throws SolrServerException, IOException;
	
}
