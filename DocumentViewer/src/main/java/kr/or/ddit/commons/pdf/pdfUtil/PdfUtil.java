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
	 * @author kis(ddit)
	 */
	public static Document createPdf(OutputStream out) throws IOException {
//		PdfWriter 인스턴스 생성
//		 * 
//		 * PdfWriter는 PDF 파일을 실제로 작성하는 객체입니다. 파일의 내용에는 신경 쓰지 않고,
//		 * PDF 문서의 파일 구조를 완성하는 역할만 합니다.
//		 * 
//		 * 생성자에 파일 경로(예: dest)를 전달하면 해당 경로에 PDF 파일이 생성됩니다.
//		 * 
//		 * 파일 대신 스트림(OutputStream)도 전달할 수 있어,
//		 * 웹 애플리케이션에서는 ServletOutputStream을,
//		 * 메모리 상에서는 ByteArrayOutputStream을 사용할 수 있습니다.
		PdfWriter writer = new PdfWriter(out);
//		PdfDocument 인스턴스 생성
//		 * 
//		 * PdfDocument는 PDF의 내용을 관리합니다.
//		 * 어떤 내용을 추가하고, 페이지를 나누고, 정보들을 정리하는 역할입니다.
//		 * 
//		 * PdfWriter와 연결되어 있어, 어떤 정보를 어떻게 쓸지 결정합니다.
		PdfDocument pdf = new PdfDocument(writer);
//		Document 인스턴스 생성
//		 * 
//		 * Document는 실제로 우리가 다루는 문서 객체입니다.
//		 * PDF와 관련된 저수준 작업은 모두 끝났고, 이제부터는 일반 문서처럼 다루면 됩니다.
//		 * 
//		 * 생성 시 PdfDocument를 전달합니다.
		Document doc = new Document(pdf);
		return doc;
	}
	
	/**
	 * 파일 생성 경로를 입력하여 pdf파일 생성을 준비하는 메서드.
	 * @param dest	pdf파일을 생성하려는 경로
	 * @return		생성된 Document 객체. pdf 형식 적용 작업 후 '.close()' 호출로 pdf 파일 생성됨.
	 * @throws 		IOException
	 * @author kis(ddit)
	 */
	public static Document createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document doc = new Document(pdf);
		return doc;
	}
	
	


}
