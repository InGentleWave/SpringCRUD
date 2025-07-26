package kr.or.ddit.commons.pdf.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.pdf.pdfTemplate.MemberPdfTemplate;
import kr.or.ddit.vo.CrudMember;

@Service
public class PdfServiceImpl implements IPdfService{

	@Override
	public ByteArrayOutputStream memberPdf(List<CrudMember> dataList, Map<String, Object> paramMap) throws Exception {
		return new MemberPdfTemplate().createPdf(dataList,paramMap);
	}

}
