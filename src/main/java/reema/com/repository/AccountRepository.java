package reema.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import reema.com.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{


}
