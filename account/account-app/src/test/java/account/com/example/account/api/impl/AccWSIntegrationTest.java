package account.com.example.account.api.impl;

import com.example.account.api.impl.AccWS;
import com.example.account.api.transaction.GetTransactionsByTransferDateRequest;
import com.example.account.comon.AccountException;
import com.example.account.domain.Transaction;
import com.example.account.helper.AccountMapper;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.FacilityRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import com.example.account.service.AccountInfoService;
import com.example.account.service.FacilityService;
import com.example.account.service.PersonService;
import com.example.account.service.TransactionService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccWSIntegrationTest {
    static AccWS accWS;
    static AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
    static PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    static AccountInfoRepository accountInfoRepository = Mockito.mock(AccountInfoRepository.class);
    static TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
    static FacilityRepository facilityRepository = Mockito.mock(FacilityRepository.class);
    static PersonService personService;
    static FacilityService facilityService;
    static TransactionService transactionService;
    static AccountInfoService accountInfoService;

    @BeforeAll
    static void preProcess() {

        facilityService = new FacilityService(facilityRepository, personRepository, accountInfoRepository, transactionRepository);
        transactionService = new TransactionService(transactionRepository, personRepository, accountInfoRepository, accountMapper);
        personService = new PersonService(personRepository, accountMapper);
        accountInfoService = new AccountInfoService(accountInfoRepository, personRepository, transactionRepository, accountMapper);

        accWS = new AccWS(personService, accountInfoService, transactionService, facilityService);
    }

    @Test
    void givenGetTransactionsByTransferDateRequest_whenfindTransactionsByTransferDate_thenGetTransactionsByTransferDateResult() {
        String code = null;
        try {
            GetTransactionsByTransferDateRequest request = Instancio.create(GetTransactionsByTransferDateRequest.class);
            when(transactionRepository.findTransactionsByTransferDate(any())).thenReturn(Instancio.ofList(Transaction.class).create());
            transactionService.getTransactionsByTransferDate(request);
        } catch (Throwable throwable) {
            code = ((AccountException) throwable).getAccountFault().getCode();
            throwable.printStackTrace();
        }
        assertEquals(code, null);
    }
}
