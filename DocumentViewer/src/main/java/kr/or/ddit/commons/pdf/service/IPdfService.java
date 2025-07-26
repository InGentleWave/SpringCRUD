package kr.or.ddit.commons.pdf.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.CrudMember;

public interface IPdfService {

	public ByteArrayOutputStream memberPdf(List<CrudMember> dataList, Map<String, Object> paramMap) throws Exception;

}
