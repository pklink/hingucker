package net.einself.sicherheitswerkzeug.member.subdomaingatherer;

import net.einself.sicherheitswerkzeug.core.data.Data;
import net.einself.sicherheitswerkzeug.member.Member;
import net.einself.sicherheitswerkzeug.core.data.DomainDataResult;
import net.einself.sicherheitswerkzeug.member.subdomaingatherer.handler.DomainHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubdomainGathererMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public void accept(Data data) {
        LOGGER.debug("Receiving data");

        if (data instanceof DomainDataResult) {
            final var domain = (DomainDataResult) data;
            LOGGER.debug("Resolves data as Subdomain data: {}", domain.getDomain());
            new DomainHandler(domain).run();
        }
    }

}
