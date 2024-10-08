package reema.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import reema.com.entity.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer>{
	
	@Query(value = "SELECT * FROM Statement WHERE account_id =:accountId and CAST(amount AS DECIMAL) BETWEEN :fromAmount AND :toAmount AND STR_TO_DATE(datefield, '%d.%m.%Y') BETWEEN STR_TO_DATE(:fromDate, '%d.%m.%Y') AND STR_TO_DATE(:toDate, '%d.%m.%Y')", nativeQuery = true)
	List<Statement> findAllByAmounAndDatefieldtRange(@Param("accountId") int accountId,  @Param("fromAmount") Double fromAmount, @Param("toAmount") Double toAmount,@Param("fromDate") String fromDate, @Param("toDate") String toDate );
	
	@Query(value = "SELECT * FROM Statement WHERE account_id =:accountId AND STR_TO_DATE(datefield, '%d.%m.%Y') BETWEEN STR_TO_DATE(:fromDate, '%d.%m.%Y') AND STR_TO_DATE(:toDate, '%d.%m.%Y')", nativeQuery = true)
	List<Statement> findAllBydDatefieldtRange(@Param("accountId") int accountId, @Param("fromDate") String fromDate, @Param("toDate") String toDate );
	
	
}
