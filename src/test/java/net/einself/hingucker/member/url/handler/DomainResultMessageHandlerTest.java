package net.einself.hingucker.member.url.handler;

import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.UrlMessage;
import net.einself.hingucker.core.messagebus.MessageBus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DomainResultMessageHandlerTest {

    @Test
    void shouldPublishHttpAndHttpsUrlMessage() {
        // arrange
        final var messageBus = mock(MessageBus.class);
        final var domainResultMessage = new DomainResultMessage("domain.com");
        final var underTest = new DomainResultMessageHandler(messageBus);

        // act
        underTest.accept(domainResultMessage);

        // assert
        final var urlDataArgument = ArgumentCaptor.forClass(UrlMessage.class);
        verify(messageBus, times(2)).publish(urlDataArgument.capture());
        assertThat(urlDataArgument.getAllValues().get(0).get()).isEqualTo("http://domain.com");
        assertThat(urlDataArgument.getAllValues().get(1).get()).isEqualTo("https://domain.com");
    }

}