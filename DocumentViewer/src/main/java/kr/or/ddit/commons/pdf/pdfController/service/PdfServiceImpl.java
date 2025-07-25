package kr.or.ddit.commons.pdf.pdfController.service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
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

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.NoticeMemberVO;

@Service
public class PdfServiceImpl implements IPdfService{
	/* -------------------------폰트 지정---------------------------*/
	// 한글 지원 폰트 
	// 프로젝트 내 경로 지정
	public static final String EXTRALIGHT = "src/main/resources/fonts/MaruBuri-ExtraLight.ttf";
	public static final String LIGHT = "src/main/resources/fonts/MaruBuri-Light.ttf";
	public static final String REGULAR = "src/main/resources/fonts/MaruBuri-Regular.ttf";
	public static final String SEMIBOLD = "src/main/resources/fonts/MaruBuri-SemiBold.ttf";
	public static final String BOLD = "src/main/resources/fonts/MaruBuri-Bold.ttf";
	
	// PdfFont 객체 선언
	public static final PdfFont ExtraLight;
	public static final PdfFont Light;
	public static final PdfFont Regular;
	public static final PdfFont SemiBold;
	public static final PdfFont Bold;
	// Font 초기화 
	static {
	    PdfFont tmpExtraLight = null;
	    PdfFont tmpLight = null;
	    PdfFont tmpRegular = null;
	    PdfFont tmpSemiBold = null;
	    PdfFont tmpBold = null;

	    try {
	        FontProgram fpExtraLight = FontProgramFactory.createFont(EXTRALIGHT);
	        FontProgram fpLight = FontProgramFactory.createFont(LIGHT);
	        FontProgram fpRegular = FontProgramFactory.createFont(REGULAR);
	        FontProgram fpSemiBold = FontProgramFactory.createFont(SEMIBOLD);
	        FontProgram fpBold = FontProgramFactory.createFont(BOLD);

	        // 폰트 임베딩 설정
	        EmbeddingStrategy embeddingStrategy = EmbeddingStrategy.FORCE_EMBEDDED;
	        
	        tmpExtraLight = PdfFontFactory.createFont(fpExtraLight, PdfEncodings.IDENTITY_H, embeddingStrategy);
	        tmpLight = PdfFontFactory.createFont(fpLight, PdfEncodings.IDENTITY_H, embeddingStrategy);
	        tmpRegular = PdfFontFactory.createFont(fpRegular, PdfEncodings.IDENTITY_H, embeddingStrategy);
	        tmpSemiBold = PdfFontFactory.createFont(fpSemiBold, PdfEncodings.IDENTITY_H, embeddingStrategy);
	        tmpBold = PdfFontFactory.createFont(fpBold, PdfEncodings.IDENTITY_H, embeddingStrategy);

	    } catch(Exception e) {
	    	// 폰트 초기화 중 예외 발생 시 처리
	        e.printStackTrace();
	    }

	    ExtraLight = tmpExtraLight;
	    Light = tmpLight;
	    Regular = tmpRegular;
	    SemiBold = tmpSemiBold;
	    Bold = tmpBold;
	}

	/*---------------------------폰트 설정 끝------------------------*/
	
	@Autowired
	private INoticeService noticeService;
	
	/**
	 * 클라이언트로부터 요청과 함께 전달받은 데이터를 바탕으로
	 * iText API를 활용하여 만든 PDF문서를
	 * ByteArrayOutputStream 객체에 담아 컨트롤러로 반환하는 메서드
	 * @return ByteArrayOutputStream iText 활용한 PDF문서 포함 
	 * @throws Exception 
	 */
	public ByteArrayOutputStream preview(List<?> dataList,Map<String,Object> paramMap) throws Exception{
		/*--------------------------------- 표로 출력할 데이터 정하기------------------------------*/		
		// VO 필드변수 정보 중 표로 출력할 변수명만 '{변수명 : 표 헤더명}' 형태로 입력
		String[][] fields = {
				{"memNo", "번호"},
				{"memId", "회원 ID"},
				{"memName", "이름"},
				{"memGender", "성별"},
				{"memEmail", "이메일"},
				{"memPhone", "연락처"},
//				{"memPostcode", "우편번호"},
				{"memAddress1", "기본 주소"},
//				{"memAddress2", "상세 주소"},
				{"memRegdate", "회원가입일"}
		};
		// 표 상대적 너비 지정 변수 colRelativeWidths
		// 문서의 최대 사용가능한 너비를 float의 배열로 입력한 비율대로 채운 표를 생성한다.
		// 배열 사이즈가 fields 사이즈와 다르면 표 생성이 제대로 이루어지지 않으므로 주의
		float[] colRelativeWidths = {1,2,2,2,5,4,8,5};
		Table memberTable = mkTableByList(dataList, fields, colRelativeWidths, Bold, Regular,10);
	    
	    
	    
	    
	    
		/*----------------------------------- 표 설정 끝 -----------------------------------------*/
		
		/*------------------------iText API 기본 구성 start--------------------------*/
		// 생성된 PDF 데이터를 담아 클라이언트로 전달할 baos 생성 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		// 미리보기 할 PDF 문서 메모리에서 생성
//		Document document = PdfUtil.createPdf(baos);
		PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));
		
		/*-------------------- 문서 크기 및 방향 선택--------------------------------*/
		// 문서 크기(기본값:A4, PageSize.'문서 규격', '직접 크기 지정'으로 변경 가능),
		PageSize pageSize = PageSize.A4;		// 문서 규격으로 선택
//		PageSize ps = new PageSize(842,680);	// 직접 크기 지정
		
		// 문서 방향(기본값:세로(portrait), rotate()로 가로(landscape)방향 선택 가능)
		pageSize = pageSize.rotate();			// 가로 방향 설정 시 주석 해제
		
		// 설정 정보를 바탕으로 document 객체 생성
		Document document = new Document(pdfDocument,pageSize);
		
		/*-------------------- PDF 문서 구성 Start--------------------------------*/
		
		document.add(new Paragraph("Member Table 멤버 테이블").setFont(Regular));
		document.add(memberTable);
		
		
		// 문서 생성 예시----
//		makeList(document,Regular);
		
		
	        
		
		
		
		
		
		
		
		// 문서 내용 구성 End-----------------------------------------
		document.close();
		
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

	    // 컬럼 개수
	    int numColumns = headerMap.size();
		if(colRelativeWidths.length != numColumns) {
			
		}
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
	            String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
	            Method getter = clazz.getMethod(getterName);
	            Object value = getter.invoke(vo);
	            String cellText = value == null ? "-" : value.toString();
//	            table.addCell(cellText);
	            table.addCell(new Cell().add(new Paragraph(cellText).setFont(bodyFont).setFontSize(fontSize).setTextAlignment(TextAlignment.CENTER)));
	        }
	    }
		
		return table;
	}
}
