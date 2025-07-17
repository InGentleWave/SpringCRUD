package kr.or.ddit.commons.pdf.pdfController.mapper;

import kr.or.ddit.commons.pdf.vo.TicketVO;

public interface ITicketMapper {

	public TicketVO findById(Long ticketId);

}
