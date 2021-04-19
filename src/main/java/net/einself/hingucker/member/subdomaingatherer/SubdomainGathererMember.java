package net.einself.hingucker.member.subdomaingatherer;

import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.Member;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.member.subdomaingatherer.handler.DomainHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class SubdomainGathererMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();
    private final MessageBus messageBus;

    @Inject
    public SubdomainGathererMember(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    @Override
    public void accept(Message message) {
        LOGGER.debug("Receiving data");

        if (message instanceof DomainResultMessage) {
            final var domain = (DomainResultMessage) message;
            LOGGER.debug("Resolves data as Subdomain data: {}", domain.getDomain());
            new DomainHandler(messageBus, domain).run();
        }
    }

}
