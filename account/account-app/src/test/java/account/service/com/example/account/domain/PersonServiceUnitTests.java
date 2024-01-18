package account.service.com.example.account.domain;

import com.example.account.AccountApplication;
import com.example.account.api.person.AddPersonRequest;
import com.example.account.api.person.GetPersonByNationalCodeRequest;
import com.example.account.api.person.GetPersonByNationalCodeResult;
import com.example.account.comon.AccountException;
import com.example.account.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(classes = AccountApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceUnitTests {

    @Autowired
    private PersonService personService;
    @ParameterizedTest
    @Order(1)
    @MethodSource("account.service.com.example.account.domain.PersonServiceUnitTestsArgumentsProvider#givenAddPersonRequestArguments")
    void givenAddPersonRequest_whenAddPerson_thenAddPersonResult(String personName, String nationalCode, String phoneNumber, String errorCode) {
        Executable e = () -> {

            AddPersonRequest request = new AddPersonRequest();
            request.setPersonName(personName);
            request.setNationalCode(nationalCode);
            request.setPhoneNumber(phoneNumber);

            personService.addPerson(request);
            assertTrue(1 == 1);
        };

        executeMethods(e, errorCode);
    }
    @ParameterizedTest
    @Order(2)
    @MethodSource("account.service.com.example.account.domain.PersonServiceUnitTestsArgumentsProvider#givenGetPersonByNationalCodeRequestArguments")
    void givenGetPersonByNationalCodeRequest_whenGetPersonByNationalCode_thenGetPersonByNationalCodeResult(String nationalCode, String errorCode) {
        Executable e = () -> {

            GetPersonByNationalCodeRequest request = new GetPersonByNationalCodeRequest();
            request.setNationalCode(nationalCode);

            GetPersonByNationalCodeResult result = personService.getPersonByNationalCode(request);

            assertTrue(result.getId() == 2);
        };

        executeMethods(e, errorCode);
    }

    private void executeMethods(Executable e, String errorCode) {
        String code = null;
        try {
            e.execute();
        } catch (Throwable throwable) {
            code = ((AccountException) throwable).getAccountFault().getCode();
            throwable.printStackTrace();
        }
        assertEquals(code, errorCode);
    }
}
