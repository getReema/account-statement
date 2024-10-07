package reema.com.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import reema.com.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query(value = "SELECT * FROM Account a , Statement s Where  a.id = s.account_id and  a.id =:accountId  and CAST(s.amount AS DECIMAL) BETWEEN :fromAmount AND :toAmount AND STR_TO_DATE(s.datefield, '%d.%m.%Y') BETWEEN STR_TO_DATE(:fromDate, '%d.%m.%Y') AND STR_TO_DATE(:toDate, '%d.%m.%Y')", nativeQuery = true)
	Set<Account> findAllByAmounAndDatefieldtRange(@Param("accountId") int accountId,  @Param("fromAmount") Double fromAmount, @Param("toAmount") Double toAmount,@Param("fromDate") String fromDate, @Param("toDate") String toDate );

	
	@Query(value = "SELECT * FROM Account a , Statement s Where  a.id =:accountId and  a.id = s.account_id and  STR_TO_DATE(s.datefield, '%d.%m.%Y') BETWEEN STR_TO_DATE(:fromDate, '%d.%m.%Y') AND STR_TO_DATE(:toDate, '%d.%m.%Y')", nativeQuery = true)
	Set<Account> findAllByDateThreeMonths(@Param("accountId") int accountId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
		
	
	@Query(value = "SELECT * FROM Account a JOIN Statement s ON a.id = s.account_id WHERE a.id = :accountId AND STR_TO_DATE(s.datefield, '%d.%m.%Y') BETWEEN STR_TO_DATE(:fromDate, '%d.%m.%Y') AND STR_TO_DATE(:toDate, '%d.%m.%Y')", nativeQuery = true)
	List<Account> giveMeThreeMonthss(@Param("accountId") int accountId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
