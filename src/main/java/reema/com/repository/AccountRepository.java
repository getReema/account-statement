package reema.com.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import reema.com.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{


}
