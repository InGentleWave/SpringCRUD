package kr.or.ddit.commons.pdf.pdfController.service;

import kr.or.ddit.commons.pdf.vo.TicketVO;

public interface ITicketService {

	public TicketVO findById(Long ticketId);

}
