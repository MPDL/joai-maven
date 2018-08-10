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
package org.dlese.dpc.vocab.tags;

import javax.servlet.jsp.JspException;

/**
 * Tag handler for rendering vocabulary as a collapsable tree menu
 *
 * @author Ryan Deardorff
 */
public class MetadataVocabTreeMenuTag extends MetadataVocabTag {
	String x;
	String y;
	String nsWidth = "0";
	String nsHeight = "0";

	/**
	 * Sets the x attribute of the MetadataVocabTreeMenuTag object
	 *
	 * @param x
	 *            The new x value
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * Sets the y attribute of the MetadataVocabTreeMenuTag object
	 *
	 * @param y
	 *            The new y value
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * Sets the nsWidth attribute of the MetadataVocabTreeMenuTag object
	 *
	 * @param nsWidth
	 *            The new nsWidth value
	 */
	public void setNsWidth(String nsWidth) {
		this.nsWidth = nsWidth;
	}

	/**
	 * Sets the nsHeight attribute of the MetadataVocabTreeMenuTag object
	 *
	 * @param nsHeight
	 *            The new nsHeight value
	 */
	public void setNsHeight(String nsHeight) {
		this.nsHeight = nsHeight;
	}

	/**
	 * Render vocab as collapsable tree menu
	 *
	 * @return
	 * @exception JspException
	 */
	public int doStartTag() throws JspException {
		try {
			setupTag(pageContext);
			pageContext.getOut().print(vocab.getVocabTreeMenu(system, language, group, pageContext));
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
