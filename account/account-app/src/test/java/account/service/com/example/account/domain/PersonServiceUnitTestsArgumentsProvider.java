package account.service.com.example.account.domain;

import org.junit.jupiter.params.provider.Arguments;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

@ActiveProfiles("test")
public class PersonServiceUnitTestsArgumentsProvider {
    public static Stream<Arguments> givenAddPersonRequestArguments(){

        return Stream.of(
                Arguments.of("samin", "5809945651", "09127142283", null),
                Arguments.of("farzane", "8906678543", "09369045589", null)
        );
    }

    public static Stream<Arguments> givenGetPersonByNationalCodeRequestArguments(){

        return Stream.of(
                Arguments.of( "8906678543", null),
                Arguments.of( "4906678543", "EP0001")
        );
    }
}
