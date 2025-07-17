package kr.or.ddit.commons.pdf.pdfUtil;

import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PdfUtil {
	
	/**
	 * OutputStream을 매개변수로 입력하여 pdf파일 생성을 준비하는 메서드
	 * @param out 	출력 OutputStream
	 * @return 		생성된 Document 객체. pdf 형식 적용 작업 후 '.close()' 호출로 pdf 파일 생성됨.
	 * @throws 		IOException
	 */
	public static Document createPdf(OutputStream out) throws IOException {
		PdfWriter writer = new PdfWriter(out);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf);
		return doc;
	}
	
	/**
	 * 파일 생성 경로를 입력하여 pdf파일 생성을 준비하는 메서드.
	 * @param dest	pdf파일을 생성하려는 경로
	 * @return		생성된 Document 객체. pdf 형식 적용 작업 후 '.close()' 호출로 pdf 파일 생성됨.
	 * @throws 		IOException
	 */
	public static Document createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf);
		return doc;
	}

}
