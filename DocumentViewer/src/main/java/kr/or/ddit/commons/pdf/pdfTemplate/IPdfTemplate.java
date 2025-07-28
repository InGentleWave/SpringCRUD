/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.pdfTemplate;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface IPdfTemplate {
	public ByteArrayOutputStream createPdf(List<?> dataList, Map<String, Object> paramMap) throws Exception;
}
