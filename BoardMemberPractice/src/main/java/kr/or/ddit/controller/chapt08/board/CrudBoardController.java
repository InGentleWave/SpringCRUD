package kr.or.ddit.controller.chapt08.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrudBoardController {

	@GetMapping("/crud/board/list")
	public String method() {
		return "chapt08/board/list";
	}
}
