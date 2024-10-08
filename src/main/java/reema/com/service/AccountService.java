package reema.com.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reema.com.entity.Account;
import reema.com.repository.AccountRepository;

@Service
public class AccountService {

	
	@Autowired
	AccountRepository accountRepository;
	
	
	public List<Account> getAllAccounts(){
		
		return accountRepository.findAll();
		
	}
	
	
	
	public Set<Account> getAccountStatmentsByAmountAndDate(int accountId, double fromAmount, double toAmount, String fromDate, String toDate){
				
		Set<Account> statements = accountRepository.findAllByAmounAndDatefieldtRange(accountId,fromAmount, toAmount, fromDate, toDate);

		
		return  statements;
	}



	public List<Account> getAccountStatmentsLastThreeMonths(int id, String fromDate, String toDate) {
	
		System.out.println("parameters "+id+ fromDate+ toDate);
		return 	accountRepository.giveMeThreeMonthss(id, fromDate, toDate);
		

	}


	
	
}
