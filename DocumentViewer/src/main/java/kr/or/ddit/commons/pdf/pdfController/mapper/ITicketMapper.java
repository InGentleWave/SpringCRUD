package kr.or.ddit.commons.pdf.pdfController.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.commons.pdf.vo.TicketVO;

@Mapper
public interface ITicketMapper {

	public TicketVO findById(Long ticketId);

}
