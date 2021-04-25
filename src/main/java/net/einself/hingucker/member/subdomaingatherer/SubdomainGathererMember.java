package net.einself.hingucker.member.subdomaingatherer;

import net.einself.hingucker.core.Member;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.member.subdomaingatherer.handler.DomainResultMessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class SubdomainGathererMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DomainResultMessageHandler domainResultMessageHandler;

    @Inject
    public SubdomainGathererMember(DomainResultMessageHandler domainResultMessageHandler) {
        this.domainResultMessageHandler = domainResultMessageHandler;
    }

    @Override
    public void accept(Message message) {
        LOGGER.debug("Receiving data");

        if (message instanceof DomainResultMessage) {
            final var domainResultMessage = (DomainResultMessage) message;
            domainResultMessageHandler.accept(domainResultMessage);
        }
    }

}
