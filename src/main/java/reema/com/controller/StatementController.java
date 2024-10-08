package reema.com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reema.com.dto.AccountStatementDTO;
import reema.com.entity.Account;
import reema.com.entity.Statement;
import reema.com.exception.BadParameters;
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

	// Return statement for the account id. consider filling date and amount range
	// http://localhost:8080/api/statement/{id}/by-amount-date-range?fromAmount=100&toAmount=200&fromDate=20.05.2020&toDate=14.10.2023
	@GetMapping("/{id}")
	public ResponseEntity<List<Account>> getStatementsPerAccount(@PathVariable("id") int id) {


		LocalDate currentDate = LocalDate.of(2020, 11, 29); // last day in db .of(2020, 11, 29)
        String fromDate = currentDate.minusMonths(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String toDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        
		    // if all is safe, proceed to fetch statament 
		    List<Account> statements = accountService.getAccountStatmentsLastThreeMonths(id,fromDate,toDate);

		    return ResponseEntity.ok(statements);
		}
	
	
	
 		// Return statement for the account id. consider filling date and amount range
		// http://localhost:8080/api/statement/{id}/by-amount-date-range?fromAmount=100&toAmount=200&fromDate=20.05.2020&toDate=14.10.2023
		@GetMapping("/{id}/by-amount-date-range")
		public ResponseEntity<Set<Account>> getStatementsPerAccountDateAmountRange(@PathVariable("id") int id,
		                                               @RequestParam(required = false) Double fromAmount,
		                                               @RequestParam(required = false) Double toAmount,
		                                               @RequestParam(required = false) String fromDate,
		                                               @RequestParam(required = false) String toDate) {

			

			    // Handle invalid amount parameters
			    if (fromAmount != null && toAmount != null && fromAmount > toAmount) {
			    	throw new BadParameters("Invalid amount parameters!");
			    }

			    // Handle missing date parameters
			    if (fromDate == null || toDate == null) {
			        // Fetch last three months if either or both dates are missing
			        LocalDate currentDate = LocalDate.now(); // last day in db .of(2020, 11, 29)
			        fromDate = currentDate.minusMonths(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			        toDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			    }

			    // Validate date format and range
			    try {
			        LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			        LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			    } catch (DateTimeParseException e) {
			    	throw new BadParameters("Invalid date parameters!");
			    }

			    if (LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")).isAfter(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
			    	throw new BadParameters("Invalid date after parameters!, fromDate is after toDate");
			    }

			    // if all is safe, proceed to fetch statament 
			    Set<Account> statements = accountService.getAccountStatmentsByAmountAndDate(id, fromAmount, toAmount, fromDate, toDate);

			    return ResponseEntity.ok(statements);
			}

}
