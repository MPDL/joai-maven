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
package org.dlese.dpc.schemedit.ndr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dlese.dpc.repository.RepositoryManager;
import org.dlese.dpc.schemedit.SchemEditUtils;
import org.dlese.dpc.schemedit.action.DCSAction;
import org.dlese.dpc.schemedit.ndr.action.form.CollectionIntegrationForm;
import org.dlese.dpc.schemedit.ndr.util.integration.MappingInfo;
import org.dlese.dpc.schemedit.ndr.util.integration.MappingsManager;

/**
 * Action supporing integration of NCS Collect Records and NDR Collections in
 * the NDR
 *
 *
 * @author Jonathan Ostwald
 *         <p>
 *
 */
public final class CollectionIntegrationAction extends DCSAction {

	private static boolean debug = true;

	private RepositoryManager rm;

	// --------------------------------------------------------- Public Methods

	/**
	 * Processes the specified HTTP request and creates the corresponding HTTP
	 * response by forwarding to a JSP that will create it.
	 *
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @param form
	 *            The ActionForm for the given page
	 * @return The ActionForward instance describing where and how control should be
	 *         forwarded
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet exception occurs
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * Design note: Only one instance of this class gets created for the app and
		 * shared by all threads. To be thread-safe, use only local variables, not
		 * instance variables (the JVM will handle these properly using the stack). Pass
		 * all variables via method signatures rather than instance vars.
		 */
		ActionErrors errors = initializeFromContext(mapping, request);
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			return (mapping.findForward("error.page"));
		}
		CollectionIntegrationForm ciForm = (CollectionIntegrationForm) form;

		/*
		 * MappingsManager.dataPath = CIGlobals.MAPPINGS_MANAGER_DATA; MappingsManager
		 * mm = MappingsManager.getInstance();
		 */

		MappingsManager mm = (MappingsManager) servlet.getServletContext().getAttribute("MappingsManager");
		if (mm == null)
			prtlnErr("MappingsManager not found in servlet context");

		ciForm.setMappingsManager(mm);
		String errorMsg = "";

		rm = repositoryManager;

		SchemEditUtils.showRequestParameters(request);

		String command = request.getParameter("command");

		try {
			if (command != null && command.equals("mappingInfo")) {
				String id = request.getParameter("id");
				if (request.getParameter("update") != null) {
					mm.update(id);
					mm.reset();
					// ciForm.setMappingsManager(MappingsManager.getInstance());
				}
				MappingInfo mappingInfo = (MappingInfo) mm.getIdMap().get(id);
				if (mappingInfo == null) {
					throw new Exception("mapping not found for id=" + id);
				}
				ciForm.setMappingInfo(mappingInfo);
				return mapping.findForward("mapping.info");
			}
			if (command != null && command.equals("resetMappings")) {
				prtln("reinitializing MappingsManager");
				mm.reset();
				// ciForm.setMappingsManager(MappingsManager.getInstance());
				prtln("  ... done");
			}
		} catch (Exception e) {
			prtlnErr("CollectionIntegrationAction caught exception.");
			if (e instanceof NullPointerException)
				e.printStackTrace();
			errors.add("error", new ActionError("generic.error", "CollectionIntegrationAction caught exception"));
		}
		saveErrors(request, errors);
		return mapping.findForward("ci.mappings");
	}

	// -------------- Debug ------------------

	/**
	 * Sets the debug attribute of the CollectionIntegrationAction class
	 *
	 * @param isDebugOutput
	 *            The new debug value
	 */
	public static void setDebug(boolean isDebugOutput) {
		debug = isDebugOutput;
	}

	/**
	 * Print a line to standard out.
	 *
	 * @param s
	 *            The String to print.
	 */
	private void prtln(String s) {
		if (debug) {
			SchemEditUtils.prtln(s, "CollectionIntegrationAction");
		}
	}

	/**
	 * NOT YET DOCUMENTED
	 *
	 * @param s
	 *            NOT YET DOCUMENTED
	 */
	private void prtlnErr(String s) {
		SchemEditUtils.prtln(s, "CollectionIntegrationAction");
	}

}
