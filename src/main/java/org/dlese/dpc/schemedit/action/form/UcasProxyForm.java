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
package org.dlese.dpc.schemedit.action.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

/**
 * ActionForm for stashing info from UcasProxyAction
 *
 * @author ostwald
 * 
 */
public class UcasProxyForm extends ActionForm {

	private boolean debug = false;
	private HttpServletRequest request;

	private String json;

	/* private SetInfo setInfo = null; */
	// input params
	/**
	 * Constructor
	 */
	public UcasProxyForm() {
	}

	/**
	 * Description of the Method
	 */
	public void clear() {

	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * Output a line of text to standard out, with datestamp, if debug is set to
	 * true.
	 *
	 * @param s
	 *            The String that will be output.
	 */
	protected final void prtln(String s) {
		if (debug) {
			System.out.println("UcasProxyForm: " + s);
		}
	}

}
