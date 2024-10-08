package reema.com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reema.com.dto.AccountStatementsDTO;
import reema.com.entity.Account;
import reema.com.exception.BadParameters;
import reema.com.service.AccountService;

@RestController
@RequestMapping("api/statement")
public class StatementController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return accounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatementsDTO> getStatementsPerAccount(@PathVariable("id") int id) {
        return accountService.getAccountStatmentsLastThreeMonths(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/by-amount-date-range")
    public ResponseEntity<AccountStatementsDTO> getStatementsPerAccountDateAmountRange(@PathVariable("id") int id,
            @RequestParam(required = false) Double fromAmount,
            @RequestParam(required = false) Double toAmount,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {

        if (fromAmount != null && toAmount != null && fromAmount > toAmount) {
            throw new BadParameters("Invalid amount parameters!");
        }

        if (fromDate == null || toDate == null) {
            LocalDate currentDate = LocalDate.now();
            fromDate = currentDate.minusMonths(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            toDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        try {
            LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BadParameters("Invalid date parameters!");
        }

        if (LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .isAfter(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
            throw new BadParameters("Invalid date after parameters!, fromDate is after toDate");
        }

        return accountService.getAccountStatmentsByAmountAndDate(id, fromAmount, toAmount, fromDate, toDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}