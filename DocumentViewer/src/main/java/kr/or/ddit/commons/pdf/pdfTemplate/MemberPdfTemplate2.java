/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package kr.or.ddit.commons.pdf.pdfTemplate;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import kr.or.ddit.commons.pdf.pdfUtil.PageNumberEventHandler;
import kr.or.ddit.commons.pdf.pdfUtil.PdfFontConfig;

public class MemberPdfTemplate2 implements IPdfTemplate {
	
	/**
	 * 클라이언트로부터 요청과 함께 전달받은 데이터를 바탕으로
	 * iText API를 활용하여 만든 PDF문서를
	 * ByteArrayOutputStream 객체에 담아 반환하는 메서드
	 * @return ByteArrayOutputStream iText 활용한 PDF문서 포함 
	 * @throws Exception 
	 */
	public ByteArrayOutputStream createPdf(List<?> dataList,Map<String,Object> paramMap) throws Exception{

		/* 0. 이번 PDF에서 사용할 font 생성------------------------------------------- */
		PdfFont extraLightFont = new PdfFontConfig().extraLightFont();
		PdfFont lightFont = new PdfFontConfig().lightFont();
		PdfFont regularFont = new PdfFontConfig().regularFont();
		PdfFont semiBoldFont = new PdfFontConfig().semiBoldFont();
		PdfFont boldFont = new PdfFontConfig().boldFont();
		/* 폰트 설정 끝---------------------------------------------------------- */
		
	/* iText API 기본 구성 start-------------------------------------------- */
		/* 1. PDF 문서 데이터 생성-------------------------------------------------*/
		
		// 생성된 PDF 데이터를 담아 클라이언트로 전달할 ByteArrayOutputStream 생성 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
//		 PdfWriter 인스턴스 생성
//		 * 
//		 * PdfWriter는 PDF 파일을 실제로 작성하는 객체입니다. 파일의 내용에는 신경 쓰지 않고,
//		 * PDF 문서의 파일 구조를 완성하는 역할만 합니다.
//		 * 
//		 * 생성자에 파일 경로(예: dest)를 전달하면 해당 경로에 PDF 파일이 생성됩니다.
//		 * 
//		 * 파일 대신 스트림(OutputStream)도 전달할 수 있어,
//		 * 웹 애플리케이션에서는 ServletOutputStream을,
//		 * 메모리 상에서는 ByteArrayOutputStream을 사용할 수 있습니다.
		PdfWriter pdfWriter = new PdfWriter(baos);
		
//		PdfDocument 인스턴스 생성
//		 * 
//		 * PdfDocument는 PDF의 내용을 관리합니다.
//		 * 어떤 내용을 추가하고, 페이지를 나누고, 정보들을 정리하는 역할입니다.
//		 * 
//		 * PdfWriter와 연결되어 있어, 어떤 정보를 어떻게 쓸지 결정합니다.
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		
		/* 2. 문서 크기 및 방향 설정------------------------------------------------*/
		// 문서 크기(기본값:A4, PageSize.'문서 규격', '직접 크기 지정'으로 변경 가능),
		PageSize pageSize = PageSize.A4;		// 문서 규격으로 선택
		//PageSize pageSize = new PageSize(842,680);	// 직접 크기 지정
		
		// 문서 방향(기본값:세로(portrait), rotate()로 가로(landscape)방향 선택 가능)
		//pageSize = pageSize.rotate();			// 가로 방향 설정 시 주석 해제
		
		// 설정 정보를 바탕으로 document 객체 생성
//		Document 인스턴스 생성
//		 * 
//		 * Document는 실제로 우리가 다루는 문서 객체입니다.
//		 * PDF와 관련된 저수준 작업은 모두 끝났고, 이제부터는 일반 문서처럼 다루면 됩니다.
//		 * 
//		 * 생성 시 PdfDocument를 전달합니다.
		Document document = new Document(pdfDocument,pageSize);
		/* 레이아웃 설정을 위한 변수 모음-------------------------------------------------*/
		float docWidth = document.getPdfDocument().getDefaultPageSize().getWidth()
                - document.getLeftMargin() - document.getRightMargin();
	/* PDF 문서 양식 데이터 생성 Start--------------------------------------------*/
		/* 1. 문서 제목 생성--------------------------------------------------*/
		// Paragraph 요소 객체는 일반적인 문단 객체입니다.
		Paragraph Title = new Paragraph("Member List 회원 리스트")
			// 메소드 체이닝으로 폰트 설정(setFont), 글자 크기 설정(setFontSize), 글자 정렬(setTextAlignment)
			// 등이 가능합니다.
			.setFont(regularFont).setFontSize(30).setTextAlignment(TextAlignment.CENTER);
		/* 2. 표로 출력할 데이터 생성	-------------------------------------------*/		
		// VO 필드변수 정보 중 표로 출력할 변수명만 '{변수명 : 표 헤더명}' 형태로 입력
		String[][] fields = {
				{"userNo", "번호"},
				{"userId", "회원 ID"},
				{"userName", "이름"},
				{"regDate", "회원가입일"},
				{"authList","회원 권한"}
		};
		// 표 상대적 너비 지정 변수 colRelativeWidths
		// 문서의 최대 사용가능한 너비를 float의 배열로 입력한 비율대로 채운 표를 생성한다.
		// 배열 사이즈가 fields 사이즈와 다르면 표 생성이 제대로 이루어지지 않으므로 주의
		float[] colRelativeWidths = {1,2,2,5,4};	// 배열 사이즈 => 생성되는 표의 컬럼 수
		// 표 생성. 원하는 순서로 document.add(테이블변수명);하면 됩니다.
		// 표 내부 스타일 설정은 mkTableByList()에 세팅해주세요.
		// 한 문서 안에 여러 개의 표 사용 시 폰트, 사이즈를 제외하고는 같은 스타일(헤더,내용의 글자 가운데 정렬 등)을 공유하게 됩니다.
		Table memberTable = mkTableByList(dataList, fields, colRelativeWidths, boldFont, 10, regularFont, 10);
		/* 표 설정 끝 --------------------------------------------------------*/
		
		
	/* PDF 문서 양식 구성하기(객체 원하는 순서대로 문서에 추가)	--------------------------------*/
		// 문서의 상단부터 원하는 요소부터 document.add(iText PDF 요소);
		
		// 문서 제목 추가
		document.add(Title);
		// Table 요소 (표 객체) 추가
		Paragraph p = new Paragraph();
		String issueDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		p.add(new Text("발행일자 : "+issueDate).setFont(regularFont));

		// TabStop: 오른쪽 끝(전체 폭)에 오른쪽 정렬
		p.addTabStops(new TabStop(docWidth, TabAlignment.RIGHT));
		p.add(new Tab());
		p.add(new Text("오른쪽 텍스트").setFont(regularFont)); // 오른쪽 텍스트 입력

		document.add(p);
		
		document.add(memberTable);
		document.add(new Paragraph("테스트1").setFont(regularFont));
		document.add(new Paragraph("테스트2").setFont(regularFont));
		
		// 문서 생성 예시----
		makeList(document,regularFont);
	/* 문서 양식 구성 끝	----------------------------------------------------*/
	/* 사용한 객체 정리		----------------------------------------------------*/
		document.close();
		pdfDocument.close();
		pdfWriter.close();
		// 현재 페이지 / 전체 페이지 정보 출력을 위한 후처리 후 반환
		// 문서마다 반복되는 Header, Footer는 PageNumberEventHandler().totalPage()에서 설정
		return new PageNumberEventHandler(regularFont).totalPage(baos);
	}
	
	private void makeList(Document document, PdfFont font) {
		// 주의사항) java.util.list가 아닌 
		// 			iText API의 com.itextpdf.layout.element.list타입입니다.
		com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List()
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
	}
	/**
	 * 매개변수로 전달된 데이터를 이용하여 만든 Table 객체를 반환하는 메서드 (표 만들기 메서드)
	 * @param dataList 표로 만드려는 VO를 담은 리스트
	 * @param fields VO의 멤버변수명과 표의 헤더명을 {키:값} 형태로 갖는 2차 배열 
	 * @param colRelativeWidths 각 컬럼의 너비 비율을 입력한 float 배열
	 * @param headerFont 표 헤더명에 적용할 PdfFont
	 * @param bodyFont 표 내용에 적용할 PdfFont
	 * @param headerFontSize 표 헤더명 글자 크기
	 * @param bodyFontSize 표 내용 글자 크기
	 * @return iTextpdf Table 요소 객체
	 * @throws Exception
	 */
	public Table mkTableByList(List<?> dataList,String[][] fields,float[] colRelativeWidths,
								PdfFont headerFont, float headerFontSize, PdfFont bodyFont, float bodyFontSize) throws Exception{
		// { vo 필드명 : 표 헤더명 }의 개수와 colRelativeWidths 개수가 일치하지 않으면 원하지 않는 표가 생성될 수 있습니다.
		
		/* 표 설정 */
		// 헤더 맵 (필드명 -> 헤더명)
	    Map<String, String> headerMap = new LinkedHashMap<>();
	    for (String[] field : fields) {
	        headerMap.put(field[0], field[1]);
	    }
	    
	    // 테이블 객체 생성
	    Table table = new Table(
	    		// 입력한 배열 비율대로 각 컬럼의 너비 비율을 정합니다.
	    		// 컬럼 개수는 변수로 입력한 colRelativeWidths 배열의 요소 개수와 같습니다. 
	    		UnitValue.createPercentArray(colRelativeWidths))
	    		// 사용가능한 모든 너비를 사용하는 옵션입니다.
				.useAllAvailableWidth();
		// 1) 헤더 추가
	    for (String header : headerMap.values()) {
	        table.addHeaderCell(new Cell().add(
        		new Paragraph(header)
        			.setFont(headerFont).setFontSize(headerFontSize)
        			.setTextAlignment(TextAlignment.CENTER))
    		);
	    }
	    
	    // 2) 데이터 행 추가
	    // dataList에 입력된 VO의 종류에 상관없이 대응할 수 있게 자바 리플렉션을 이용했습니다.
	    // 리플렉션을 통해 컴파일 시점에 정해지지 않은(동적으로 결정되는) 메서드를 런타임에 호출할 수 있습니다.
	    // 행마다 다른 색을 넣기 위한 rowIndex
	    int rowIndex = 0;
	    
	    for (Object vo : dataList) {
	    	// dataList의 요소인 VO의 클래스를 clazz에 저장합니다.
	    	Class<?> clazz = vo.getClass();
	    	// headerMap에 key로 저장했던,
	    	// field[][] 2차 배열의 vo 변수명을 fieldName에 할당하여 반복문을 수행합니다.
	    	// VO가 동적으로 결정되기 때문에 컴파일 시점에 존재하지 않는 VO의 getter메서드를
	    	// 사용가능하게 하는 코드입니다.
	        for (String fieldName : headerMap.keySet()) {
	        	// cellText에 헤더명에 일치하는 컬럼내용을 저장할 예정입니다.
	        	String cellText = "";
	        	// VO(여기서는 CrudMemberVO)의 멤버변수인 
	        	// list(여기서는 List<CrudMemberAuth> authList)의 
	        	// 내부 속성값(여기서는 CrudMemberAuth의 auth)들을 출력하기 위해
	        	// fieldName으로 조건 분기하여 처리합니다.
	        	// 해당 조건 분기를 사용하기 위해 동적으로 결정되는 VO의 
	        	// List타입 멤버변수명이 List로 끝나야 합니다.
	        	if(fieldName.endsWith("List")) {
	        		// 자바 리플렉션 ::: Class타입 변수 clazz를 이용하여 getter메서드를 만드는 과정입니다.
	        		// fieldName 정보를 이용하여 @getter 어노테이션이 자동 생성하는 getter메서드명을 만들어 getterName에 저장합니다.
	        		String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
	        		// getterName과 같은 이름의 매개변수가 없는 public 메서드를 Method 타입 getter 변수에 저장합니다.
		            Method getter = clazz.getMethod(getterName);
		            // getter 메서드를 동적으로 실행(invoke)합니다.
		            // 호출 대상 객체는 vo(여기서는 CrudMember)입니다.
		            // fieldList에 vo의 리스트타입 멤버변수(여기서는 List<CrudMemberAuth> authList)가 할당됩니다. 
		            List<?> fieldList =(List<?>) getter.invoke(vo);
		            if(fieldList != null && fieldList.size()>0) {
		            	// fieldList의 값이 존재하면
		            	// fieldList의 타입인 vo2(여기서는 CrudMemberAuth)에 대한 반복문을 수행합니다.
		            	for(Object vo2 : fieldList) {
		            		// 동적으로 결정된 vo2의 getter메서드를 만드는 과정입니다.
		            		// Class타입의 내장 메서드를 이용해서 만드므로 clazz2를 생성합니다.
		            		Class<?> clazz2 = vo2.getClass();
		            		// 이 부분에 전달된 fieldName이 authList이고,
		            		// getter메서드를 사용해서 얻으려는 속성이 auth이므로 fieldName을 사용했습니다.
		            		// fieldName을 이용할 수 없는 경우에는 적절한 값을 담은 String 변수를 fieldName자리에 사용해주세요.
		            		getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.lastIndexOf("List"));
		            		// 현재 getterName == "getAuth"
		            		getter = clazz2.getMethod(getterName);
		            		// 현재 getter는 getAuth()와 같습니다.
		            		
		            		// vo2(여기서는 CrudMemberAuth)에 getter(여기서는 getAuth())를 호출한 결과가
		            		// value에 할당됩니다. 여기서는 DB에 존재하는 "ROLE_MEMBER"나 "ROLE_ADMIN", 그 외 값이 SQL쿼리 결과에 따라 할당됩니다.
		            		Object value = getter.invoke(vo2);
		            		// 여러 개의 내용을 표 1칸에 출력하고 싶은 경우 사용합니다.
		            		// 여기서는 CrudMember의 권한이 여러 개인 경우를 표현하기 위해 사용했습니다.
		            		// 리팩토링 시 { DB내의 정보 : 출력 메시지 }의 키:값 쌍을 매개변수로 사용하여 출력 가능할 것 같습니다. 
		            		if(value != null && !value.toString().equals("")) {
			            		if(value.toString().equals("ROLE_MEMBER")) {
			            			cellText += "회원, ";
			            		} else if(value.toString().equals("ROLE_ADMIN")) {
			            			cellText += "관리자, ";
			            		}
		            		}
		            	}
		            	// cellText에 추가한 문자열의 마지막 쉼표(", ")를 제거하는 코드입니다.
		            	if(cellText.contains(", ")) {
		            		cellText = cellText.substring(0,cellText.lastIndexOf(", "));
		            	} else {
		            		// 여기서는 getter를 통해 얻은 value가 null이거나
		            		// DB에 저장된 정보가 "ROLE_MEMBER"나 "ROLE_ADMIN"이 아닌 경우에 
		            		// 아래 내용을 출력합니다.
		            		cellText = "인가 정보가 존재하지 않습니다.";
		            	}
		            }
	        	} else {
	        		// fieldName이 List가 아닌 경우의 Cell 추가 과정입니다.
		            String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		            Method getter = clazz.getMethod(getterName);
		            Object value = getter.invoke(vo);
		            // DB에서 조회된 결과가 없거나 ""인 경우 '-'을 출력합니다.
		            // 날짜 타입 정보는 형식에 맞게 변환합니다.
		            if(fieldName.contains("Date")) {
		            	cellText = value == null||value == "" ? "-" : new SimpleDateFormat("yyyy년 MM월 dd일").format(value);
		            } else {
		            	cellText = value == null||value == "" ? "-" : value.toString();
		            }
	        	}
	        	// 배경색 설정: 짝수 행은 흰색, 홀수 행은 연회색
	    	    DeviceRgb bgColor = (rowIndex % 2 == 0) ? new DeviceRgb(255, 255, 255) : new DeviceRgb(230, 230, 230);
	        	// 테이블에 1칸씩 Cell객체를 추가합니다.
	            // new Paragraph()자리에 문자열이나 다른 객체를 넣을 수 있습니다.
	            // new Paragraph()객체를 사용함으로써 메소드체이닝으로 해당 내용에 대한 세팅을 간편하게 설정할 수 있습니다.
	        	table.addCell(new Cell()
	        		.setBackgroundColor(bgColor)
        			.add(new Paragraph(cellText)
        			.setFont(bodyFont).setFontSize(bodyFontSize)
        			.setTextAlignment(TextAlignment.CENTER))
    			);
	        }
	        rowIndex++;
	    }
		
		return table;
	}

}
