package net.einself.hingucker.member.url;

import net.einself.hingucker.core.data.DomainDataResult;
import net.einself.hingucker.core.data.UrlData;
import net.einself.hingucker.databus.DataBus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UrlMemberTest {

    @Test
    void domainDataResultShouldPublishHttpAndHttpsUrlData() {
        // arrange
        final var dataBus = mock(DataBus.class);
        final var urlMember = new UrlMember(dataBus);
        final var domainDataResult = new DomainDataResult("domain.com");

        // act
        urlMember.accept(domainDataResult);

        // assert
        final var urlDataArgument = ArgumentCaptor.forClass(UrlData.class);
        verify(dataBus, times(2)).publish(urlDataArgument.capture());
        assertThat(urlDataArgument.getAllValues().get(0).get()).isEqualTo("http://domain.com");
        assertThat(urlDataArgument.getAllValues().get(1).get()).isEqualTo("https://domain.com");
    }

}