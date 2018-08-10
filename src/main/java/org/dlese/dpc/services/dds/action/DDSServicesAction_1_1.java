/*
	Copyright 2017 Digital Learning Sciences (DLS) at the
	University Corporation for Atmospheric Research (UCAR),
	P.O. Box 3000, Boulder, CO 80307

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package org.dlese.dpc.services.dds.action;

import org.dlese.dpc.index.ResultDoc;
import org.dlese.dpc.index.reader.XMLDocReader;
import org.dlese.dpc.services.dds.action.form.DDSServicesForm;

/**
 * An <strong>Action</strong> that handles DDS Web service requests. This class
 * handles DDSWS service version 1.1, overriding methods in the base class as
 * needed.
 *
 * @author John Weatherley
 * @version $Id: DDSServicesAction_1_1.java,v 1.5 2009/03/20 23:33:59 jweather
 *          Exp $
 * @see org.dlese.dpc.services.dds.action.form.DDSServicesForm_1_1
 */
public final class DDSServicesAction_1_1 extends DDSServicesAction {

	/**
	 * Gets the key used for caching the ListCollections response in the application
	 * scope. Should be overwritten by the subclasses and updated in the
	 * ListCollections.jsp page when a new version is created.
	 */
	protected String getListCollectionsCacheKey() {
		return "ListCollectionsResponse11";
	}

	/**
	 * Sets the record XML in the form bean using the stripped but not localized
	 * version of XML.
	 *
	 * @param df
	 *            The form
	 * @param resultDoc
	 *            The resultDoc
	 */
	protected void setRecordXml(DDSServicesForm df, ResultDoc resultDoc) {
		df.setRecordXml(((XMLDocReader) resultDoc.getDocReader()).getXmlStripped());
	}
}
