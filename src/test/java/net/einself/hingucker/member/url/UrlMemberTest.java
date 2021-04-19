package net.einself.hingucker.member.url;

import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.member.url.handler.DomainResultMessageHandler;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UrlMemberTest {

    @Test
    void shouldRouteDomainResultMessageToDomainResultMessageHandler() {
        // arrange
        final var domainDataResult = new DomainResultMessage("domain.com");
        final var domainResultMessageHandler = mock(DomainResultMessageHandler.class);
        final var underTest = new UrlMember(domainResultMessageHandler);

        // act
        underTest.accept(domainDataResult);

        // assert
        verify(domainResultMessageHandler).accept(domainDataResult);
    }

}