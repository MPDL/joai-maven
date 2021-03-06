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
package org.dlese.dpc.vocab.tags.opml;

import javax.servlet.jsp.JspException;

import org.dlese.dpc.vocab.MetadataVocab;

/**
 * Tag handler for retreiving vocabulary responses re-ordered/grouped as OPML
 *
 * @author Ryan Deardorff
 */
public class MetadataVocabGetResponseOPMLTag extends MetadataVocabTag {

	/**
	 * Get OPML subset defined by responses cached using MetadataVocabSetResponseTag
	 *
	 * @return
	 * @exception JspException
	 */
	public int doStartTag() throws JspException {
		try {
			String contextAttributeName = (String) pageContext.getServletContext()
					.getInitParameter("metadataVocabInstanceAttributeName");
			vocab = (MetadataVocab) pageContext.findAttribute(contextAttributeName);
			if (vocab == null) {
				throw new JspException("Vocabulary not found");
			}
			pageContext.getOut().print(vocab.getResponseOPML(pageContext));
		} catch (java.io.IOException ex) {
			throw new JspException(ex.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * Description of the Method
	 *
	 * @return
	 * @exception JspException
	 */
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * Description of the Method
	 */
	public void release() {
		super.release();
	}
}
