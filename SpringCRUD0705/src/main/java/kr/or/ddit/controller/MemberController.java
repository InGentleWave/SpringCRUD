package kr.or.ddit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private IMemberService memberService;
	
	@GetMapping("/form.do")
	public String memberForm() {
		log.info("memberForm()실행");
		return "form";
	}
	@PostMapping("/insert.do")
	public String memberInsert(MemberVO memberVO, Model model) {
		log.info("memberInsert()실행");
		log.debug("memberVO : " + memberVO);
		String goPage="";
		int status = memberService.memberInsert(memberVO);
		if(status>0) {
			goPage = "redirect:/detail.do?memId="+memberVO.getMemId();
		} else {
			goPage = "form";
			model.addAttribute("member",memberVO);
		}
		return goPage;
	}
}
