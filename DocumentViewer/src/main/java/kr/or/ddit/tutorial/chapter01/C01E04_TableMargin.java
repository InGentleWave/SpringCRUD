/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.tutorial.chapter01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

/**
 * Simple table example.
 */
public class C01E04_TableMargin {
	// table 생성에 사용할 가짜 데이터 경로
	public static final String DATA = "src/main/resources/data/united_states.csv";
	// 완성한 Pdf 파일 저장 경로
	public static final String DEST = "results/chapter01/united_states.pdf";

	public static void main(String args[]) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new C01E04_TableMargin().createPdf(DEST);
	}

	public void createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);

		// 생성자를 통해
		// 문서 크기(기본값:A4, PageSize.'문서 규격'으로 변경 가능),
		// 문서 방향(기본값:세로(portrait), rotate()로 가로(landscape)방향 선택 가능)
		Document document = new Document(pdf, PageSize.A4.rotate());

		// Document 생성자에서 마진 지정
		// Document(PageSize, left, right, top, bottom)
		// 네 개의 숫자는 각각 왼쪽, 오른쪽, 위, 아래 마진(포인트 단위, 1포인트=0.3528mm)입니다.
		document.setMargins(20, 20, 20, 20);

		// 2개의 폰트 객체 생성
		// 테이블의 내용 부분에 적용할 폰트 font
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		// 테이블의 헤더 부분에 적용할 폰트 bold
		PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		// 테이블 객체 생성
		Table table = new Table(
		// 9개의 열을 가진 Table 객체를 생성합니다. 각 열의 상대적인 너비를 float 배열로 정의합니다.
		// 첫 번째 열은 두 번째 열보다 네 배 넓고, 세 번째 열은 두 번째 열보다 세 배 넓습니다. 이런 식으로 각 열의 너비를 지정합니다.
				UnitValue.createPercentArray(new float[] { 4, 1, 3, 4, 3, 3, 3, 3, 1 }))
		// 테이블의 전체 너비도 페이지의 사용 가능한 너비(여백 제외)에 맞춰 설정합니다.
		// 이 경우 테이블은 페이지 너비의 100%를 사용합니다.
				.useAllAvailableWidth();
		
		// DATA : 미리 준비한 csv파일의 경로 
		BufferedReader br = new BufferedReader(new FileReader(DATA));
		// csv파일(가짜 데이터)의 정보를 한 줄씩 읽는다.
		String line = br.readLine();
		// 첫번째 줄이 csv파일의 헤더 정보임을 process()마지막 매개변수 자리에 true값을 넣어서 알린다.
		process(table, line, bold, true);
		// 두번째로 읽어온 줄부터는 헤더가 아닌 데이터 정보임을 process()에 false값을 전달해서 알린다.
		while ((line = br.readLine()) != null) {
			process(table, line, font, false);
		}
		br.close();
		document.add(table);

		// Close document
		document.close();
	}

	/**
	 * 반복문을 통해 한 줄씩 읽어온 데이터를 테이블 객체에 한 행씩 추가하기 위한 메서드
	 * @param table 행 정보(line)을 추가할 테이블 객체
	 * @param line 추가할 행 정보
	 * @param font 추가할 행의 폰트 정보
	 * @param isHeader 추가하는 행이 헤더이면 true, 데이터이면 false
	 */
	public void process(Table table, String line, PdfFont font, boolean isHeader) {
		// csv 파일은 열을 ';'로 구분하므로
		// 문자열을 구분자로 분리해주는 StringTokenizer 객체를 이용하여 반복문을 사용한다. 
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		int length = tokenizer.countTokens();
		int i = 1;
		while (tokenizer.hasMoreTokens()) {
			// 입력된 행 정보의 헤더 정보 여부에 따라 테이블 객체에 추가한다.
			// 헤더 정보의 경우, 테이블의 첫 줄에 출력된다..
			// 테이블이 문서의 다음 페이지까지 이어지는 경우,
			// 헤더 정보가 이어진 페이지의 테이블 첫 줄에 재출력된다.
			// new Cell() 메서드는 한 칸의 column 객체다.
			// column 객체에 add()메서드를 체이닝하여 내용을 설정한다.
			// add()의 매개변수로 문단 객체를 전달하고,
			// 전달하는 문단 객체에 setFont()를 메서드 체이닝하여 폰트를 설정할 수 있다.
			// addCell()로 추가되는 새로운 셀이 기존 행의 마지막에 붙이는 셀인지 다음 행의 첫 셀인지는
			// 테이블 객체가 생성될 시에 입력받은 열 개수 정보를 이용하여 자동으로 처리한다.
			if (isHeader) {
				// table에 헤더 열 정보를 하나씩 추가하는 메서드 addHeaderCell()
				// table에 데이터 열 정보를 하나씩 추가하는 addCell()
				table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			} else {
				table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			}
		}
	}
}