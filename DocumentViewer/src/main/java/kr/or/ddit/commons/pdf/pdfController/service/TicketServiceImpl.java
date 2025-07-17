package kr.or.ddit.commons.pdf.pdfController.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.pdf.pdfController.mapper.ITicketMapper;
import kr.or.ddit.commons.pdf.vo.TicketVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TicketServiceImpl implements ITicketService{
	
	@Autowired
	private ITicketMapper mapper;
	
	@Override
	public TicketVO findById(Long ticketId) {
		return mapper.findById(ticketId);
	}

}
