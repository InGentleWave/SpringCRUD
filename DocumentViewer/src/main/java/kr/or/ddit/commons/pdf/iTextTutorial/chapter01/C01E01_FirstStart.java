/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.iTextTutorial.chapter01;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class C01E01_FirstStart {

	//
	public static final String DEST = "results/chapter01/hello_world.pdf";

	public static void main(String[] args) throws IOException {
		// 문자열 대신 OutputStream 객체도 생성자 매개변수에 사용 가능.
		// 예를 들어 웹 앱을 위해서 ServletOutputStream 객체를 매개변수로 받거나
		// 메모리에 저장된 PDF 문서를 만들기 위해 ByteArrayOutputStream을 매개변수로 받을 수 있음
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new C01E01_FirstStart().createPdf(DEST);
		
	}

	private void createPdf(String dest) throws IOException {
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
		PdfWriter writer = new PdfWriter(dest);

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
		Document document = new Document(pdf);

//		Paragraph 추가
//		 * 
//		 * "Hello World!"라는 텍스트가 담긴 Paragraph(단락)를 만들어서 document에 추가합니다.
		document.add(new Paragraph("Hello World!"));

//		문서 닫기
//		 * 
//		 * document.close()를 호출하면 PDF 파일이 완성되어 저장됩니다.
		document.close();
		
		writer.close();

	}
}
