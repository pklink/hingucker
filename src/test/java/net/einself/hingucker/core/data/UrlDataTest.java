package net.einself.hingucker.core.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlDataTest {

    @Test
    void getterShouldReturnUrl() {
        // arrange
        final var underTest = new UrlData("FOO");

        // act
        final var result = underTest.get();

        // assert
        assertThat(result).isEqualTo("FOO");
    }

}