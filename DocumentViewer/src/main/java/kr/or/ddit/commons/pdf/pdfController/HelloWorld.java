package kr.or.ddit.commons.pdf.pdfController;

import java.io.IOException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class HelloWorld {
	public static String ROOT = "results/";
	public static void main(String[] args) throws IOException {
		
		PdfWriter writer = new PdfWriter(ROOT+"HelloWorld.pdf");
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		ImageData img = 
		Image image = new Image(null);
		// 기본 텍스트 출력
		document.add(new Paragraph("Hello, PDF World!"));
	
		// TODO: 이미지 삽입
		document.add()
		// TODO: 한글 폰트 설정
		// TODO: 페이지 마진 적용
	
		document.close();
		writer.close();
	}
}
