package reema.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reema.com.entity.Statement;
import reema.com.repository.StatementRepository;

@Service
public class StatementService {
	
	@Autowired 
	StatementRepository statementRepo;
	
	
	
	public List<Statement> getAccountStatmentsByAmountAndDate(int accountId, double fromAmount, double toAmount, String fromDate, String toDate){
		
		List<Statement> statements = statementRepo.findAllByAmounAndDatefieldtRange(accountId,fromAmount, toAmount, fromDate, toDate);

		
		return  statements;
	}
	
	public List<Statement> getAccountStatmentsLastThreeMonths(int accountId, String fromDate, String toDate){
		
		return  statementRepo.findAllBydDatefieldtRange(accountId, fromDate, toDate);

		
		  
	}
	


}
