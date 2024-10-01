package reema.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reema.com.entity.Account;
import reema.com.entity.Statement;
import reema.com.service.AccountService;
import reema.com.service.StatementService;


@RestController
@RequestMapping("api/statement")
public class StatementController {
	

	@Autowired
	private StatementService statementService;
	
	@Autowired
	AccountService accountService;
	
	
	// get all accounts
	// http://localhost:8080/api/statement/
	@GetMapping("/")
	public List<Account> getAllAccounts(){
		
		List<Account> accounts = accountService.getAllAccounts();
		
		if(accounts.size()==0)
			return null;
		
		return accounts; 
		
	}
	
	// get statment by account ID 
	// http://localhost:8080/api/statement/{id}
	@GetMapping("{id}")
	public List<Statement> getStatementsPerAccount(@PathVariable("id") int id){
		
		List<Statement> statments = statementService.getStatments(id);
		
		if(statments.size()==0)
			return null;
		
		return statementService.getStatments(id); // should be passing long L here ?
		
	}
	
	
	//get statement by amount range 
	//http://localhost:8080/api/statement/by-amount-range?fromAmount=100&toAmount=200
	 @GetMapping("/by-amount-range")
	    public List<Statement> getTransactionsByAmountRange(@RequestParam Double fromAmount, @RequestParam Double toAmount) {
	        return statementService.getTransactionsByAmountRange(fromAmount, toAmount);
	    }
	
	 // get statement by amount & date range 
	 @GetMapping("/by-amount-date-range")
	    public List<Statement> getTransactionsByAmountDateRange(@RequestParam Double fromAmount, @RequestParam Double toAmount,@RequestParam String fromDate, @RequestParam String toDate ) {
	        return statementService.getTransactionsByAmountAndDate(fromAmount, toAmount, fromDate, toDate);
	    }
	
	

}
