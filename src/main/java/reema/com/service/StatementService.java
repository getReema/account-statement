package reema.com.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reema.com.dto.AccountStatementDTO;
import reema.com.entity.Account;
import reema.com.entity.Statement;
import reema.com.repository.AccountRepository;
import reema.com.repository.StatementRepository;

@Service
public class StatementService {
	
	@Autowired 
	StatementRepository statementRepo;
	
	@Autowired 
	AccountRepository accountRepo;
	

	public List<Statement> getStatments(int Id){
		
		return statementRepo.findAllByAccountId(Id); // int for testing 
		
	}

	
	
	public List<Statement> getTransactionsByAmountRange(double from, double to){
	
		return statementRepo.findAllByAmountRange(from, to);
	}
	
	
	
	public Set<Account> getTransactionsByAmountAndDate(int accountId, double fromAmount, double toAmount, String fromDate, String toDate){
				
	
		Set<Account> statements = accountRepo.findAllByAmounAndDatefieldtRange(accountId,fromAmount, toAmount, fromDate, toDate);

		
		return  statements;
	}
}
