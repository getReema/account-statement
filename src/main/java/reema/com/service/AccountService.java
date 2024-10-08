package reema.com.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reema.com.dto.AccountStatementsDTO;
import reema.com.entity.Account;
import reema.com.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatementService statementService;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountStatementsDTO> getAccountStatmentsByAmountAndDate(int accountId, double fromAmount, double toAmount,
            String fromDate, String toDate) {

        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            return Optional.empty();
        }

        AccountStatementsDTO accountStatementDto = new AccountStatementsDTO();
        accountStatementDto.setAccountId(account.get().getId());
        accountStatementDto.setAccountNumber(account.get().getAccountNumber());
        accountStatementDto.setAccountType(account.get().getAccountType());
        accountStatementDto.setStatements(
                statementService.getAccountStatmentsByAmountAndDate(accountId, fromAmount, toAmount, fromDate, toDate));

        return Optional.of(accountStatementDto);
    }

    public Optional<AccountStatementsDTO> getAccountStatmentsLastThreeMonths(int accountId) {

        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            return Optional.empty();
        }

        AccountStatementsDTO accountStatementDto = new AccountStatementsDTO();
        accountStatementDto.setAccountId(account.get().getId());
        accountStatementDto.setAccountNumber(account.get().getAccountNumber());
        accountStatementDto.setAccountType(account.get().getAccountType());

        LocalDate currentDate = LocalDate.of(2020, 11, 29);
        String fromDate = currentDate.minusMonths(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String toDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        accountStatementDto.setStatements(statementService.getAccountStatmentsLastThreeMonths(accountId, fromDate, toDate));

        return Optional.of(accountStatementDto);
    }
}