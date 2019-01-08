package vn.hue.kienletrung.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hue.kienletrung.springboot.DAO.BankAccountDAO;
import vn.hue.kienletrung.springboot.Exception.BankException;
import vn.hue.kienletrung.springboot.FORM.Form;
import vn.hue.kienletrung.springboot.model.BankAccountInfo;

@Controller
public class Controllers {
	@Autowired
	private BankAccountDAO bankAccountDao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showBankAccount(Model model) {
		List<BankAccountInfo> list = bankAccountDao.getBankAccount();
		model.addAttribute("accountInfos", list);
		return "accountsPage";
	//	return "hello";
	}

	@RequestMapping(value = "/sendMoneyPage", method = RequestMethod.GET)
	public String viewSendMoneyPage(Model model) {

		Form form = new Form(1L, 2L, 700d);

		model.addAttribute("sendMoneyForm", form);

		return "sendMoneyPage";
	}

	@RequestMapping(value = "/sendMoneyPage", method = RequestMethod.POST)
	public String processSendMoney(Model model, Form sendMoneyForm) throws BankException {

		System.out.println("Send Money::" + sendMoneyForm.getAmmount());
		try {
		bankAccountDao.sendMoney(sendMoneyForm.getFromId(), //
				sendMoneyForm.getToId(), //
				sendMoneyForm.getAmmount());
		}
		catch (BankException e ) {
			model.addAttribute("e","error : "+e.getMessage());
			
		}
		return "redirect:/";
	}
	@RequestMapping(value="/ex")
	public String ex(Model model) {
		return "ex";
	}

}
