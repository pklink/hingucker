package net.einself.hingucker.member.url;

import net.einself.hingucker.DataBus;
import net.einself.hingucker.core.data.Data;
import net.einself.hingucker.core.data.DomainDataResult;
import net.einself.hingucker.core.data.UrlData;
import net.einself.hingucker.member.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void accept(Data data) {
        LOGGER.debug("Received data {}", data.getClass().getName());

        if (data instanceof DomainDataResult) {
            final var domain = (DomainDataResult) data;
            LOGGER.debug("Resolve data as Domain data: {}", domain.getDomain());
            publishUrl(domain);
        }
    }

    private void publishUrl(DomainDataResult domain) {
        publishUrl("http", domain);
        publishUrl("https", domain);
    }

    private void publishUrl(String protocol, DomainDataResult domain) {
        final var url = String.format("%s://%s", protocol, domain.getDomain());
        DataBus.INSTANCE.publish(new UrlData(url));
    }

}
