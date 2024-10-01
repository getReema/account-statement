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
	

	public List<Statement> getStatments(int Id){
		
		return statementRepo.findAllByAccountId(Id); // int for testing 
		
	}

	
	
	public List<Statement> getTransactionsByAmountRange(double from, double to){
	
		return statementRepo.findAllByAmountRange(from, to);
	}
	
	
	public List<Statement> getTransactionsByAmountAndDate(double fromAmount, double toAmount, String fromDate, String toDate){
		
		return statementRepo.findAllByAmounAndDatefieldtRange(fromAmount, toAmount, fromDate, toDate);
	}
}
