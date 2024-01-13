package account.service.person;

import com.example.account.comon.AccountErrorsStatic;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class PersonServiceUnitTestsArgumentsProvider {
    public static Stream<Arguments> givenGetPersonByNationalCodeRequestArguments() throws IOException {

        return Stream.of(
                Arguments.of("1074890063", null),
                Arguments.of("8074891063", AccountErrorsStatic.ERROR_PERSON_NOT_FOUND)
        );
    }
}
