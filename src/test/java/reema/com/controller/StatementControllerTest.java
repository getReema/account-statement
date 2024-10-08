package reema.com.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import reema.com.dto.AccountStatementsDTO;
import reema.com.entity.Account;
import reema.com.exception.BadParameters;
import reema.com.service.AccountService;

public class StatementControllerTest {

    @Mock
    private AccountService accountService;
    
    @InjectMocks
    private StatementController statementController;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllAccounts() {
        when(accountService.getAllAccounts()).thenReturn(Collections.emptyList());
        
        ResponseEntity<List<Account>> response = statementController.getAllAccounts();
        
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetStatementsPerAccount_NotFound() {
        int accountId = 1;
        when(accountService.getAccountStatmentsLastThreeMonths(accountId))
            .thenReturn(Optional.empty());

        ResponseEntity<AccountStatementsDTO> response = statementController.getStatementsPerAccount(accountId);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetStatementsPerAccountDateAmountRange_InvalidAmount() {
        assertThatThrownBy(() -> statementController.getStatementsPerAccountDateAmountRange(1, 200.0, 100.0, "20.05.2020", "14.10.2023"))
            .isInstanceOf(BadParameters.class)
            .hasMessageContaining("Invalid amount parameters!");
    }
    
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetStatementsPerAccountDateAmountRange_InvalidDate() {
        assertThatThrownBy(() -> statementController.getStatementsPerAccountDateAmountRange(1, 100.0, 200.0, "invalid-date", "14.10.2023"))
            .isInstanceOf(BadParameters.class)
            .hasMessageContaining("Invalid date parameters!");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetStatementsPerAccountDateAmountRange_NotFound() {
        int accountId = 1;
        when(accountService.getAccountStatmentsByAmountAndDate(accountId, 100.0, 200.0, "20.05.2020", "14.10.2023"))
            .thenReturn(Optional.empty());

        ResponseEntity<AccountStatementsDTO> response = statementController.getStatementsPerAccountDateAmountRange(accountId, 100.0, 200.0, "20.05.2020", "14.10.2023");

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}