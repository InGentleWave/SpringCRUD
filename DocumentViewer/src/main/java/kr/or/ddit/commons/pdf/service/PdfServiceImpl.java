/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.pdf.pdfTemplate.MemberPdfTemplate;
import kr.or.ddit.commons.pdf.pdfTemplate.sample;
import kr.or.ddit.vo.CrudMember;

@Service
public class PdfServiceImpl implements IPdfService{

	@Override
	public ByteArrayOutputStream memberPdf(List<CrudMember> dataList, Map<String, Object> paramMap) throws Exception {
		return new MemberPdfTemplate().createPdf(dataList,paramMap);
	}

	@Override
	public ByteArrayOutputStream sample(Map<String, Object> paramMap) throws Exception {
		return new sample().createPdf(null, paramMap);
	}


}
