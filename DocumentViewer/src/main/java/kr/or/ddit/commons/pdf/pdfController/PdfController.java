package kr.or.ddit.commons.pdf.pdfController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import kr.or.ddit.commons.pdf.pdfController.service.IPdfService;
import kr.or.ddit.commons.pdf.pdfUtil.PdfUtil;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.NoticeMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/pdf")
public class PdfController {
	
	@Autowired
	private INoticeService noticeService;
	
	@Autowired
	private IPdfService pdfService;
	/**
	 * 클라이언트로부터 요청과 함께 전달받은 데이터를 바탕으로
	 * iText API를 활용하여 만든 PDF문서를
	 * ResponseEntity 객체에 담아 반환하는 메서드
	 * @return ResponseEntity<byte[]> iText 활용한 PDF문서 포함 
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping("/preview")
	public ResponseEntity<byte[]> preview(
//			@RequestParam(required = false) List<?> dataList,
//			@RequestParam(required = false) Map<String,Object> paramMap
			) throws Exception{
		
		List<NoticeMemberVO> dataList = noticeService.selectMemberList();
		Map<String,Object> paramMap = new HashMap<>();
		// 가공한 데이터(원하는 VO의 리스트, 문서 양식이나 기타 정보를 담은 맵)를 pdfService.preview()에 전달하면
		// 완성된 PDF 문서가 담긴 ByteArrayOutputStream를 반환합니다. 
		ByteArrayOutputStream baos = pdfService.preview(dataList,paramMap);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ticket.pdf")
		        .contentType(MediaType.APPLICATION_PDF)
		        .body(baos.toByteArray());
	}
	
	@ResponseBody
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadPdf() throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    Document document = PdfUtil.createPdf(baos);
	    document.add(new Paragraph("Download다운 Test테스트"));
	    document.close();
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
	        .contentType(MediaType.APPLICATION_PDF)
	        .body(baos.toByteArray());
	}
}
