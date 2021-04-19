package net.einself.hingucker.member.url;

import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.Member;
import net.einself.hingucker.member.url.handler.DomainResultMessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UrlMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DomainResultMessageHandler domainResultMessageHandler;

    @Inject
    public UrlMember(DomainResultMessageHandler domainResultMessageHandler) {
        this.domainResultMessageHandler = domainResultMessageHandler;
    }

    @Override
    public void accept(Message message) {
        LOGGER.debug("Received data {}", message.getClass().getName());

        if (message instanceof DomainResultMessage) {
            final var domain = (DomainResultMessage) message;
            domainResultMessageHandler.accept(domain);
        }
    }

}
