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

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

/**
 * Simple image example.
 */
public class C01E03_ImageParagraph {
//	public static final String DOG = "src/main/resources/img/sample.jpg";
	public static final String DOG = "src/main/resources/img/chopa.jpg";
//	public static final String DOG = "src/main/resources/img/python.bmp";
	public static final String FOX = "src/main/resources/img/smiley.bmp";

	public static final String DEST = "results/chapter01/quick_brown_fox.pdf";

	public static void main(String args[]) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new C01E03_ImageParagraph().createPdf(DEST);
	}

	public void createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);

//		4. 문단(Paragraph)과 이미지 추가
//		이미지를 문단에 삽입할 수 있습니다.
//
//		예시에서는 두개의 이미지를 문단에 추가하는 방법을 보여줍니다.
		// 이미지 객체 생성
		Image fox = new Image(ImageDataFactory.create(FOX));
		Image dog = new Image(ImageDataFactory.create(DOG));
		// 생성자 매개변수에 넣은 문자열로 시작하는 문단 객체 생성
		// 메서드 체이닝을 통해 원하는 순서대로 이미지 객체나 문자열을 기존 문단에 추가할 수 있다.
		Paragraph p = new Paragraph("The quick brown ").add(fox).add(" jumps over the lazy ").add(dog);
		// 완성한 문단 객체를 문서 객체에 추가한다.
		document.add(p);

		// Close document
		document.close();
	}
}