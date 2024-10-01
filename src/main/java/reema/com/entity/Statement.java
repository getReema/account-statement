package reema.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statement")
public class Statement {
	
	 @Id
	    private Long id;
	    private Long accountId;
	    private String datefield;
	    private String amount;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getAccountId() {
			return accountId;
		}
		public void setAccountId(Long accountId) {
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
