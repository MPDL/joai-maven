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
package org.dlese.dpc.schemedit.test;

import org.dlese.dpc.serviceclients.remotesearch.RemoteResultDoc;
import org.dlese.dpc.serviceclients.remotesearch.RemoteSearcher;

/**
 * Utilities for manipulating XPaths, represented as String
 *
 * @author ostwald
 */
public class RemoteSearchTester {

	private static boolean debug = true;

	/**
	 * The main program for the RemoteSearchTester class
	 *
	 * @param args
	 *            The command line arguments
	 * @exception Exception
	 *                Description of the Exception
	 */
	public static void main(String[] args) throws Exception {

		String targetUrl;
		String serviceBaseUrl;
		String service = "dlese";

		if (service == "dcs") {
			targetUrl = "http://www.state.me.us/doc/nrimc/pubedinf/crest/activity/act33.htm";
			serviceBaseUrl = "http://dcs.dlese.org/dcc/services/ddsws1-0";
		} else if (service == "dlese") {
			targetUrl = "http://www.pulseplanet.com/archive/Sep04/3281.html";
			serviceBaseUrl = "http://www.dlese.org/dds/services/ddsws1-0";
		} else
			throw new Exception("service (" + service + ") not recognized");

		RemoteSearcher rs = new RemoteSearcher(serviceBaseUrl, null);

		RemoteResultDoc[] results = rs.searchDocs(targetUrl);

	}

	/**
	 * Description of the Method
	 *
	 * @param s
	 *            Description of the Parameter
	 */
	private static void prtln(String s) {
		if (debug) {
			System.out.println(s);
		}
	}
}
