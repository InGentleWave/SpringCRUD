package kr.or.ddit.commons.pdf.pdfTemplate;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import kr.or.ddit.commons.pdf.pdfUtil.PdfFontConfig;

public class MemberPdfTemplate implements IPdfTemplate {
	
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
		
		// 생성된 PDF 데이터를 담아 클라이언트로 전달할 baos 생성 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		// 미리보기 할 PDF 문서 메모리에서 생성
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		
		/* 2. 문서 크기 및 방향 설정------------------------------------------------*/
		// 문서 크기(기본값:A4, PageSize.'문서 규격', '직접 크기 지정'으로 변경 가능),
		PageSize pageSize = PageSize.A4;		// 문서 규격으로 선택
		//PageSize ps = new PageSize(842,680);	// 직접 크기 지정
		
		// 문서 방향(기본값:세로(portrait), rotate()로 가로(landscape)방향 선택 가능)
		//pageSize = pageSize.rotate();			// 가로 방향 설정 시 주석 해제
		
		// 설정 정보를 바탕으로 document 객체 생성
		Document document = new Document(pdfDocument,pageSize);
		
	/* PDF 문서 양식 데이터 생성 Start--------------------------------------------*/
		
		/* 1. 표로 출력할 데이터 생성	-------------------------------------------*/		
		// VO 필드변수 정보 중 표로 출력할 변수명만 '{변수명 : 표 헤더명}' 형태로 입력
		String[][] fields = {
				{"userNo", "번호"},
				{"userId", "회원 id"},
				{"userName", "이름"},
				{"regDate", "회원가입일"},
				{"authList","회원 권한"}
		};
		// 표 상대적 너비 지정 변수 colRelativeWidths
		// 문서의 최대 사용가능한 너비를 float의 배열로 입력한 비율대로 채운 표를 생성한다.
		// 배열 사이즈가 fields 사이즈와 다르면 표 생성이 제대로 이루어지지 않으므로 주의
		float[] colRelativeWidths = {1,2,2,5,4};	// 배열 사이즈 => 생성되는 표의 컬럼 수
		// 표 생성. 원하는 순서로 document.add(테이블변수명);하면 됩니다.
		Table memberTable = mkTableByList(dataList, fields, colRelativeWidths, boldFont, regularFont,10);
		/* 표 설정 끝 --------------------------------------------------------*/
		
		
	/* PDF 문서 양식 구성하기(객체 순서대로 배치)	--------------------------------*/
		document.add(new Paragraph("Member List 회원 리스트").setFont(regularFont).setFontSize(30).setTextAlignment(TextAlignment.CENTER));
		document.add(memberTable);
		
		
		// 문서 생성 예시----
		//makeList(document,Regular);
		
		
	        
		
		
		
		
		
		
		
	/* 문서 양식 구성 끝	----------------------------------------------------*/
	/* 사용한 객체 정리		----------------------------------------------------*/
		document.close();
		pdfDocument.close();
		pdfWriter.close();
		return baos;
	}
	
	private void makeList(Document document, PdfFont font) {
		// 주의사항) java.util.list가 아닌 iText API의 list타입입니다.
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
	public Table mkTableByList(List<?> dataList,String[][] fields,float[] colRelativeWidths,
								PdfFont headerFont, PdfFont bodyFont, float fontSize) throws Exception{
		/* 표 설정 */
		// 헤더 맵 (필드명 -> 헤더명)
	    Map<String, String> headerMap = new LinkedHashMap<>();
	    for (String[] field : fields) {
	        headerMap.put(field[0], field[1]);
	    }
	    
	    // 컬럼 개수는 변수로 입력한 
		
	    // 테이블 객체 생성
	    Table table = new Table(UnitValue.createPercentArray(
	    		colRelativeWidths))
				.useAllAvailableWidth();
		// 1) 헤더 추가
	    for (String header : headerMap.values()) {
	        table.addHeaderCell(new Cell().add(new Paragraph(header).setFont(headerFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	    }
	    
	    // 2) 데이터 행 추가
	    // dataList에 입력된 VO의 종류에 상관없이 대응할 수 있게 자바 리플렉션을 이용했습니다.
	    // 리플렉션을 통해 컴파일 시점에 정해지지 않은(동적으로 결정되는) 메서드를 런타임에 호출할 수 있습니다.
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
		            // 테이블에 1칸씩 Cell객체를 추가합니다.
		            // new Paragraph()자리에 문자열이나 다른 객체를 넣을 수 있습니다.
		            // new Paragraph()객체를 사용함으로써 메소드체이닝으로 해당 내용에 대한 세팅을 간편하게 설정할 수 있습니다.
	        		table.addCell(new Cell().add(new Paragraph(cellText).setFont(bodyFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	        	} else {
	        		// fieldName이 List가 아닌 경우의 Cell 추가 과정입니다.
		            String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		            Method getter = clazz.getMethod(getterName);
		            Object value = getter.invoke(vo);
		            // DB에서 조회된 결과가 없거나 ""인 경우 '-'을 출력합니다.
		            cellText = value == null||value == "" ? "-" : value.toString();
		            table.addCell(new Cell().add(new Paragraph(cellText).setFont(bodyFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	        	}
	        }
	    }
		
		return table;
	}

}
