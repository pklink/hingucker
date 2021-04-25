package net.einself.hingucker.member.subdomaingatherer;

import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.member.subdomaingatherer.handler.DomainResultMessageHandler;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SubdomainGathererMemberTest {

    @Test
    void shouldDelegateDomainResultMessageToDomainResultMessageHandler() {
        // arrange
        final var domainResultMessageHandler = mock(DomainResultMessageHandler.class);
        final var domainResultMessage = mock(DomainResultMessage.class);
        final var underTest = new SubdomainGathererMember(domainResultMessageHandler);

        // act
        underTest.accept(domainResultMessage);

        // assert
        verify(domainResultMessageHandler).accept(domainResultMessage);
    }

    @Test
    void shouldNOtDelegateGenericMessageToDomainResultMessageHandler() {
        // arrange
        final var domainResultMessageHandler = mock(DomainResultMessageHandler.class);
        final var message = mock(Message.class);
        final var underTest = new SubdomainGathererMember(domainResultMessageHandler);

        // act
        underTest.accept(message);

        // assert
        verifyZeroInteractions(domainResultMessageHandler);
    }

}