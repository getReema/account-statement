package reema.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statement")
public class Statement {
	
	 @Id
	    private int id;
	    private int accountId;
	    private String datefield;
	    private String amount;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAccountId() {
			return accountId;
		}
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		public String getDateField() {
			return datefield;
		}
		public void setDateField(String dateField) {
			this.datefield = dateField;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
	
	    

}
