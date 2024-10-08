package reema.com.dto;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;

import jakarta.persistence.Entity;
import reema.com.entity.Statement;

@Component
public class AccountStatementsDTO {
	
	/*
	 * display all the information of the statement by doing two fetches 
	 * 
	 * first execute get information of the account 
	 * then get information of the statment then set it to that object 
	 * 
	 */
	//account
    private int accountId;
    private String accountType;
    private String accountNumber; // display it as hash 
   
    
    List <Statement> statements = new ArrayList<>();

    
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		// return the account number hashded using sha 265
		return Hashing.sha256().hashString(accountNumber, StandardCharsets.UTF_8)
		        .toString();
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<Statement> getStatements() {
		return statements;
	}
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	

}
