package pl.sdacademy.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PersonNegativeAgeArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("ftrela@gmail.com", 0),
                Arguments.of("ftrela@gmail.com", -1),
                Arguments.of("ftrela@gmail.com", null));
    }
}
