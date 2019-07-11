package pl.sdacademy.javagda25;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.Month;
import java.util.stream.Stream;

public class ProvaideNegativeArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(Month.FEBRUARY),
                Arguments.of(Month.APRIL),
                Arguments.of(Month.JUNE),
                Arguments.of(Month.SEPTEMBER),
                Arguments.of(Month.NOVEMBER)
        );
    }
}
