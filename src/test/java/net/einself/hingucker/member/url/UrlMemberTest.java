package net.einself.hingucker.member.url;

import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.UrlMessage;
import net.einself.hingucker.core.messagebus.MessageBus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UrlMemberTest {

    @Test
    void domainDataResultShouldPublishHttpAndHttpsUrlData() {
        // arrange
        final var dataBus = mock(MessageBus.class);
        final var urlMember = new UrlMember(dataBus);
        final var domainDataResult = new DomainResultMessage("domain.com");

        // act
        urlMember.accept(domainDataResult);

        // assert
        final var urlDataArgument = ArgumentCaptor.forClass(UrlMessage.class);
        verify(dataBus, times(2)).publish(urlDataArgument.capture());
        assertThat(urlDataArgument.getAllValues().get(0).get()).isEqualTo("http://domain.com");
        assertThat(urlDataArgument.getAllValues().get(1).get()).isEqualTo("https://domain.com");
    }

}