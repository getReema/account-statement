package reema.com.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import reema.com.entity.Account;
import reema.com.exception.BadParameters;
import reema.com.service.AccountService;
import reema.com.service.StatementService;

public class StatementControllerTest {

    @Mock
    private StatementService statementService;
    
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
        
        List<Account> result = statementController.getAllAccounts();
        
        assertThat(result).isNull();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetStatementsPerAccount() {
        int accountId = 1;
        when(accountService.getAccountStatmentsLastThreeMonths(anyInt(), anyString(), anyString()))
            .thenReturn(Collections.emptyList());

        ResponseEntity<List<Account>> response = statementController.getStatementsPerAccount(accountId);

        assertThat(response.getBody()).isEmpty();
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
}