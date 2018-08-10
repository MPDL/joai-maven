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

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.dlese.dpc.xml.schema.DocMap;

/**
 * ActionForm bean for handling requests to support Schemaedit. Most methods
 * acesss the {@link DocMap} attribute, which wraps the XML Document that is
 * being edited.
 *
 * @author ostwald
 */
public class FrameworkAdminForm extends ActionForm {

	private boolean debug = true;

	private Map frameworks = null;
	private List unloadedFrameworks = null;
	private List uninitializedFrameworks = null;

	/**
	 * Description of the Field
	 */
	// protected FormFile sampleFile;

	public Map getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(Map map) {
		frameworks = map;
	}

	public void setUnloadedFrameworks(List formats) {
		this.unloadedFrameworks = formats;
	}

	public List getUnloadedFrameworks() {
		return this.unloadedFrameworks;
	}

	public void setUninitializedFrameworks(List formats) {
		this.uninitializedFrameworks = formats;
	}

	public List getUninitializedFrameworks() {
		return this.uninitializedFrameworks;
	}

	/**
	 * Description of the Method
	 *
	 * @param s
	 *            Description of the Parameter
	 */
	private void prtln(String s) {
		if (debug) {
			System.out.println("SchemaEditAdminForm: " + s);
		}
	}
}
