package net.einself.hingucker.member.url;

import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.UrlMessage;
import net.einself.hingucker.core.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UrlMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final MessageBus messageBus;

    @Inject
    public UrlMember(MessageBus messageBus) {
        this.messageBus = messageBus;
    }


    @Override
    public void accept(Message message) {
        LOGGER.debug("Received data {}", message.getClass().getName());

        if (message instanceof DomainResultMessage) {
            final var domain = (DomainResultMessage) message;
            LOGGER.debug("Resolve data as Domain data: {}", domain.getDomain());
            publishUrl(domain);
        }
    }

    private void publishUrl(DomainResultMessage domain) {
        publishUrl("http", domain);
        publishUrl("https", domain);
    }

    private void publishUrl(String protocol, DomainResultMessage domain) {
        final var url = String.format("%s://%s", protocol, domain.getDomain());
        messageBus.publish(new UrlMessage(url));
    }

}
