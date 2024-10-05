package reema.com.service;

import java.util.List;

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
	
	
}
