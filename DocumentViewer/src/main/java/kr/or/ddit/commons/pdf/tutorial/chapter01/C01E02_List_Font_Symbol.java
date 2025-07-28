/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.tutorial.chapter01;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

/**
 * Simple List example.
 */
public class C01E02_List_Font_Symbol {
        public static final String DEST = "results/chapter01/rick_astley.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E02_List_Font_Symbol().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // 1. document 객체 생성
    	PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
//      2. 기본 폰트 변경
//        iText는 기본적으로 Helvetica 폰트를 사용합니다.
//        폰트를 변경하려면 PdfFontFactory를 사용해 새로운 PdfFont 객체를 만듭니다.
//        이 폰트 객체를 Paragraph와 List에 적용할 수 있습니다.
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
        
//      3. 리스트(List) 추가
//        List 객체를 생성해 글머리표 리스트(불릿 리스트)를 만듭니다.
        // Collection의 List가 아닌 com.itextpdf.layout.element.list의 List입니다.
        List list = new List()
    		// 리스트 항목들은 사용자 단위(예: 12pt)만큼 들여쓰기가 적용됩니다.
            .setSymbolIndent(12)
	        // 글머리표 리스트(불릿 리스트)를 만듭니다.
            .setListSymbol("\u2022")
            // 기본 폰트를 설정합니다.
            .setFont(font);
        // 리스트 항목(ListItem)을 여섯 개 추가합니다.
        list.add(new ListItem("Never gonna give you up"))
            .add(new ListItem("Never gonna let you down"))
            .add(new ListItem("Never gonna run around and desert you"))
            .add(new ListItem("Never gonna make you cry"))
            .add(new ListItem("Never gonna say goodbye"))
            .add(new ListItem("Never gonna tell a lie and hurt you"));
        // 완성된 리스트를 문서에 추가합니다.
        document.add(list);
        document.close();
        writer.close();
    }
}