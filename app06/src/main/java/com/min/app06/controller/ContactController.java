package com.min.app06.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app06.dto.ContactDTO;
import com.min.app06.service.ContactService;

@RequestMapping(value = "/contact")
@Controller
public class ContactController {

  private ContactService contactService;
  
  @Autowired  
  public ContactController(ContactService contactService) {
    super();
    this.contactService = contactService;
  }

  @GetMapping(value = "/list.do")
  public String list(Model model) {
    model.addAttribute("contactList", contactService.getContactList());
    return "contact/list";
  }
  
  @GetMapping(value = "/detail.do")
  public String wp_detail(@RequestParam(value = "contactNo", required = false, defaultValue = "0") int contactNo
                        , Model model) {
    model.addAttribute("contact", contactService.getContactByNo(contactNo));
    return "contact/detail";
  }
  
  @GetMapping(value = "/write.do")
  public String write() {
    return "contact/write";
  }
  
  @PostMapping(value = "/register.do")
  public String wp_register(ContactDTO contact
                          , RedirectAttributes rttr) {
    String redirectURL, registerResult;
    if(contactService.tx_registerContact(contact) == 1) {
      redirectURL = "/contact/list.do";
      registerResult = "연락처 등록 성공";
    } else {
      redirectURL = "/contact/write.do";
      registerResult = "연락처 등록 실패";
    }
    rttr.addFlashAttribute("registerResult", registerResult);
    return "redirect:" + redirectURL;
  }
  
  @PostMapping(value = "/modify.do")
  public String wp_modify(ContactDTO contact
                        , RedirectAttributes rttr) {
    String modifyResult = contactService.tx_modifyContact(contact) == 1 ? "연락처 수정 성공" : "연락처 수정 실패";
    rttr.addAttribute("contactNo", contact.getContactNo())
        .addFlashAttribute("modifyResult", modifyResult);
    return "redirect:/contact/detail.do?contactNo={contactNo}";
  }
  
  @GetMapping(value = "/remove.do")
  public String wp_remove(@RequestParam(value = "contactNo", required = false, defaultValue = "0") int contactNo
                        , RedirectAttributes rttr) {
    Map<String, Object> map = new HashMap<>();
    if(contactService.tx_removeContact(contactNo) == 1) {
      map.put("redirectURL", "/contact/list.do");
      map.put("removeResult", "연락처 삭제 성공");
    } else {
      map.put("redirectURL", "/contact/detail.do?contactNo={contactNo}");
      map.put("removeResult", "연락처 삭제 실패");
    }
    rttr.addAttribute("contactNo", contactNo)
        .addFlashAttribute("removeResult", map.get("removeResult"));
    return "redirect:" + map.get("redirectURL");
  }
  
  @GetMapping(value = "/removes.do")
  public String wp_removes(@RequestParam(value = "contactNo") String[] contactNoList
                         , RedirectAttributes rttr) {
    String removeListResult = contactService.tx_removeContactList(contactNoList) == contactNoList.length ? "선택한 모든 연락처 삭제 성공" : "선택한 연락처 삭제 실패";
    rttr.addFlashAttribute("removeListResult", removeListResult);
    return "redirect:/contact/list.do";
  }
  
}
