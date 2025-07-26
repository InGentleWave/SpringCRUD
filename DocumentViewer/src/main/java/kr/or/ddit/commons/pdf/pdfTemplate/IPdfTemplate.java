package kr.or.ddit.commons.pdf.pdfTemplate;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface IPdfTemplate {
	public ByteArrayOutputStream createPdf(List<?> dataList, Map<String, Object> paramMap) throws Exception;
}
