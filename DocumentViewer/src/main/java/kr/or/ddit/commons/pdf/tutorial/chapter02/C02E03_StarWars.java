/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.tutorial.chapter02;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple drawing text example.
 */
public class C02E03_StarWars {

    public static final String DEST = "results/chapter02/star_wars.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C02E03_StarWars().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {

        // PDF 문서 초기화 (지정한 파일 경로에 PDF 생성)
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // A4 사이즈의 새 페이지 생성
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);

        // PDF 페이지에 그릴 수 있는 낮은 레벨의 그래픽 객체 생성
        PdfCanvas canvas = new PdfCanvas(page);

        // 화면에 출력할 텍스트 목록 생성 (스타워즈 Intro 텍스트)
        List<String> text = new ArrayList();
        text.add("         Episode V         ");
        text.add("  THE EMPIRE STRIKES BACK  ");
        text.add("It is a dark time for the");
        text.add("Rebellion. Although the Death");
        text.add("Star has been destroyed,");
        text.add("Imperial troops have driven the");
        text.add("Rebel forces from their hidden");
        text.add("base and pursued them across");
        text.add("the galaxy.");
        text.add("Evading the dreaded Imperial");
        text.add("Starfleet, a group of freedom");
        text.add("fighters led by Luke Skywalker");
        text.add("has established a new secret");
        text.add("base on the remote ice world");
        text.add("of Hoth...");

        // 좌표계의 기준점을 페이지 좌측 상단(Top Left)으로 이동시킴
        // PDF 기본 좌표계는 좌측 하단 (Left-Bottom)이기 때문에 위에서 아래로 텍스트를 출력하려 통합 변환 행렬 적용
        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());

        // 텍스트 출력 모드 시작
        canvas.beginText()
            // 폰트 설정 (Courier Bold, 사이즈 14pt)
            .setFontAndSize(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD), 14)
            // 줄간격 설정 (폰트 크기의 1.2배 사용)
            .setLeading(14 * 1.2f)
            // 텍스트 출력 시작 좌표 설정: x = 70, y = -40 (왼쪽 상단 모서리 기준 아래로 40pt 내려감)
            .moveText(70, -40);

        // 위에서 정의한 문자열 리스트를 줄 단위로 출력
        for (String s : text) {
            // 한 줄 출력 후 커서를 다음 줄로 이동
            canvas.newlineShowText(s);
        }

        // 텍스트 출력 모드 종료
        canvas.endText();

        // PDF 문서 닫기 (리소스 해제 및 파일 저장)
        pdf.close();
    }

}