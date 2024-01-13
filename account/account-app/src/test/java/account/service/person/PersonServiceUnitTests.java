package account.service.person;

import com.example.account.api.person.GetPersonByNationalCodeRequest;
import com.example.account.api.person.GetPersonByNationalCodeResult;
import com.example.account.service.PersonService;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.account.AccountApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AccountApplication.class)
public class PersonServiceUnitTests {

    @Autowired
    private PersonService personService;

    @ParameterizedTest
    @MethodSource("account.service.person.PersonServiceUnitTestsArgumentsProvider#givenGetPersonByNationalCodeRequestArguments")
    void givenGetPersonByNationalCodeRequest_whenGetPersonByNationalCode_thenGetPersonByNationalCodeResult(String nationalCode, String errorCode) {
        Executable e = () -> {
            GetPersonByNationalCodeRequest request = new GetPersonByNationalCodeRequest();
            request.setNationalCode(nationalCode);

            GetPersonByNationalCodeResult result = personService.getPersonByNationalCode(request);

            assertTrue(result.getId() != 0);
        };

        try {
            e.execute();
        } catch (Throwable throwable) {
            assertEquals(throwable.getMessage().split(":")[0], errorCode);
            throwable.printStackTrace();
        }

    }
}
