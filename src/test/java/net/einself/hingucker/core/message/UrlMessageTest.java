package net.einself.hingucker.core.message;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlMessageTest {

    @Test
    void getterShouldReturnUrl() {
        // arrange
        final var underTest = new UrlMessage("FOO");

        // act
        final var result = underTest.get();

        // assert
        assertThat(result).isEqualTo("FOO");
    }

}