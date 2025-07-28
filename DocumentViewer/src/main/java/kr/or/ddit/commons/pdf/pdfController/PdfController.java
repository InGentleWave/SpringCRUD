/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2023 Apryse Group NV
    Authors: Apryse Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.pdf.service.IPdfService;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.CrudMember;
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
	@PostMapping("/memberList")
	public ResponseEntity<byte[]> memberPdf(
			@RequestParam(name="auth",required=false) List<String> authList 
//			@RequestParam(required = false) Map<String,Object> paramMap // 데이터 정제 조건을 담은 맵
			) throws Exception{
		// 멤버리스트 서식에 필요한 데이터 조회
		List<CrudMember> dataList = noticeService.selectCrudMemberList(authList);
		// 확장성을 위한 변수를 담을 Map
		Map<String,Object> paramMap = new HashMap<>();
		// 가공한 데이터(원하는 VO의 리스트, 문서 양식이나 기타 정보를 담은 맵)를 pdfService.preview()에 전달하면
		// 완성된 PDF 문서가 담긴 ByteArrayOutputStream를 반환합니다. 
		ByteArrayOutputStream baos = pdfService.memberPdf(dataList,paramMap);
		/*
			3) PDF 생성 시점과 메모리 사용
			ByteArrayOutputStream에 PDF를 생성 후 byte[]로 클라이언트 전송하는 방식은 보통 잘 쓰입니다만,
			큰 문서일 때는 메모리 부담이 크므로 스트리밍 방식이나 임시 파일 사용도 고민해 볼 수 있음.
			(6) 에러 처리
			데이터 조회나 PDF 생성 중 오류가 발생하면 적절한 예외 처리 및 에러 메시지를 클라이언트에 보내야 합니다.
			PDF 생성 실패 시 미리 준비된 에러 페이지 PDF, 또는 JSON 에러 메시지를 반환하는 등 사용자 경험을 고려하세요.
		 */
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ticket.pdf")
		        .contentType(MediaType.APPLICATION_PDF)
		        .body(baos.toByteArray());
	}
}
