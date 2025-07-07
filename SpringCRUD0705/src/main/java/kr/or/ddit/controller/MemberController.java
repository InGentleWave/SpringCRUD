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
	
	@GetMapping("/detail.do")
	public String memberDetail(String memId, Model model) {
		log.info("memberDetail()실행");
		log.debug("memId : {}",memId);
		MemberVO memberVO = memberService.memberDetail(memId);
		model.addAttribute("member",memberVO);
		return "view";
	}
	
	@GetMapping("/update.do")
	public String memberUpdateForm(String memId,Model model) {
		log.info("memberUpdateForm()실행");
		log.debug("memId : {}",memId);
		MemberVO memberVO = memberService.memberDetail(memId);
		model.addAttribute("member",memberVO);
		model.addAttribute("status","u");
		return "updateForm";
	}
	
	@PostMapping("/update.do")
	public String memberUpdate(MemberVO memberVO,Model model) {
		log.info("memberUpdate()실행");
		log.debug("memberVO : {}",memberVO);
		String goPage="";
		int status = memberService.memberUpdate(memberVO);
		if(status>0) {
			goPage="redirect:/detail.do?memId="+memberVO.getMemId();
		} else {
			goPage="updateForm";
			model.addAttribute("member",memberVO);
		}
		return goPage;
	}
}
