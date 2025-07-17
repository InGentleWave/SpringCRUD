package kr.or.ddit.commons.pdf.pdfController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import kr.or.ddit.commons.pdf.pdfController.service.ITicketService;
import kr.or.ddit.commons.pdf.pdfUtil.PdfUtil;
import kr.or.ddit.commons.pdf.vo.TicketVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/pdf")
public class PdfController {
	
	@Autowired
	private ITicketService ticketService;
	
	@ResponseBody
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadPdf(@RequestParam Long ticketId) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    TicketVO ticket = ticketService.findById(ticketId);

	    Document document = PdfUtil.createPdf(baos);
	    document.add(new Paragraph());
	    
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.pdf")
	        .contentType(MediaType.APPLICATION_PDF)
	        .body(baos.toByteArray());
	}

}
