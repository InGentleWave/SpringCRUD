package kr.or.ddit.commons.pdf.pdfController.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface IPdfService {

	public ByteArrayOutputStream preview(List<?> dataList, Map<String, Object> paramMap) throws Exception;

}
