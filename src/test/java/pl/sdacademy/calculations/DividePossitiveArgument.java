package pl.sdacademy.calculations;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DividePossitiveArgument implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(1, 1, 1),
                Arguments.of(1, 5, 0.2),
                Arguments.of(15.0, 3.0, 5)
        );
    }
}
