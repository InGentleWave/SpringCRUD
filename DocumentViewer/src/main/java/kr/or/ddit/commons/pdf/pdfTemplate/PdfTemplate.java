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

public class PdfTemplate implements IPdfTemplate {
	
	/**
	 * 클라이언트로부터 요청과 함께 전달받은 데이터를 바탕으로
	 * iText API를 활용하여 만든 PDF문서를
	 * ByteArrayOutputStream 객체에 담아 반환하는 메서드
	 * @return ByteArrayOutputStream iText 활용한 PDF문서 포함 
	 * @throws Exception 
	 */
	public ByteArrayOutputStream createPdf(List<?> dataList,Map<String,Object> paramMap) throws Exception{
		/* ----------------------------이번 PDF에서 사용할 font 생성------------------------------- */
		PdfFont extraLightFont = new PdfFontConfig().extraLightFont();
		PdfFont lightFont = new PdfFontConfig().lightFont();
		PdfFont regularFont = new PdfFontConfig().regularFont();
		PdfFont semiBoldFont = new PdfFontConfig().semiBoldFont();
		PdfFont boldFont = new PdfFontConfig().boldFont();
		/* --------------------------------폰트 설정 끝--------------------------------- */
		/*--------------------------------- 표로 출력할 데이터 정하기------------------------------*/		
		// VO 필드변수 정보 중 표로 출력할 변수명만 '{변수명 : 표 헤더명}' 형태로 입력
		String[][] fields = {
		//	{ 변수명1 		: 표 헤더명1},		
		//	{ 변수명2 		: 표 헤더명2},		
		//	{ 변수명3 		: 표 헤더명3},		
		//		      	 :
		//	{ 마지막 변수명 	: 마지막 표 헤더명}		
		};
		// 표 상대적 너비 지정 변수 colRelativeWidths
		// 문서의 최대 사용가능한 너비를 float의 배열로 입력한 비율대로 채운 표를 생성한다.
		// 배열 사이즈가 fields 사이즈와 다르면 표 생성이 제대로 이루어지지 않으므로 주의
		float[] colRelativeWidths = {/*fields의 {변수명:표 헤더명} 개수만큼 숫자1,숫자2,..마지막 숫자*/};	// 배열 사이즈 => 생성되는 표의 컬럼 수
		// 표 생성
		// 매개변수 (출력할 vo의 리스트, 출력할 변수명과 그에 대응하는 헤더명, 표 비율, 헤더 폰트, 바디 폰트, 폰트 사이즈)
		Table table = mkTableByList(dataList, fields, colRelativeWidths, boldFont, regularFont,10);
		// 표 생성 후 원하는 순서에 document.add(테이블변수명);하면 됩니다.
		/*----------------------------------- 표 설정 끝 -----------------------------------------*/
		
		/*------------------------iText API 기본 구성 start--------------------------*/
		// 생성된 PDF 데이터를 담아 클라이언트로 전달할 baos 생성 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter pdfWriter = new PdfWriter(baos);
		// 미리보기 할 PDF 문서 메모리에서 생성
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		
		/*-------------------- 문서 크기 및 방향 선택--------------------------------*/
		// 문서 크기(기본값:A4, PageSize.'문서 규격', '직접 크기 지정'으로 변경 가능),
		PageSize pageSize = PageSize.A4;		// 문서 규격으로 선택
//		PageSize ps = new PageSize(842,680);	// 직접 크기 지정
		
		// 문서 방향(기본값:세로(portrait), rotate()로 가로(landscape)방향 선택 가능)
//		pageSize = pageSize.rotate();			// 가로 방향 설정 시 주석 해제
		
		// 설정 정보를 바탕으로 document 객체 생성
		Document document = new Document(pdfDocument,pageSize);
		
		/*-------------------- PDF 문서 양식 구성 Start--------------------------------*/
		// 새로운 문자열을 문서에 삽입하는 코드(원하는 순서에 배치하세요.)
		document.add(new Paragraph("Member Table 멤버 테이블").setFont(regularFont)
						.setTextAlignment(TextAlignment.CENTER));
		// 생성한 테이블 문서에 삽입하는 코드(원하는 순서에 배치하세요.)
		document.add(table);
		
		
		// 문서 생성 예시----
//		makeList(document,Regular);
		
		
	        
		
		
		
		
		
		
		
		// 문서 내용 구성 End-----------------------------------------
		document.close();
		pdfDocument.close();
		pdfWriter.close();
		return baos;
	}
	
	private void makeList(Document document, PdfFont font) {
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
	    for (Object vo : dataList) {
	    	Class<?> clazz = vo.getClass();
	        for (String fieldName : headerMap.keySet()) {
	        	String cellText = "";
	        	// authList 내부 auth들을 출력하기 위한 부분
	        	if(fieldName.endsWith("List")) {
	        		String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		            Method getter = clazz.getMethod(getterName);
		            // List<CrudMemberAuth> list =(List<?>) getter.invoke(vo);
		            List<?> list =(List<?>) getter.invoke(vo);
		            if(list != null && list.size()>0) {
		            	for(Object vo2 : list) {
		            		Class<?> clazz2 = vo2.getClass();
		            		// fieldName = authList
		            		getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.lastIndexOf("List"));
		            		getter = clazz2.getMethod(getterName);
		            		// String auth
		            		Object value = getter.invoke(vo2);
		            		if(value != null && !value.toString().equals("")) {
			            		if(value.toString().equals("ROLE_MEMBER")) {
			            			cellText += "회원, ";
			            		} else if(value.toString().equals("ROLE_ADMIN")) {
			            			cellText += "관리자, ";
			            		}
		            		}
		            	}
		            	if(cellText.contains(", ")) {
		            		cellText = cellText.substring(0,cellText.lastIndexOf(", "));
		            	} else {
		            		cellText = "인가 정보가 존재하지 않습니다.";
		            	}
		            }
	        		table.addCell(new Cell().add(new Paragraph(cellText).setFont(bodyFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	        	} else {
		            String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		            Method getter = clazz.getMethod(getterName);
		            Object value = getter.invoke(vo);
		            cellText = value == null ? "-" : value.toString();
	//	            table.addCell(cellText);
		            table.addCell(new Cell().add(new Paragraph(cellText).setFont(bodyFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	        	}
	        }
	    }
		
		return table;
	}

}
