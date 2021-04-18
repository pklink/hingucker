package net.einself.hingucker.member.subdomaingatherer;

import net.einself.hingucker.core.data.Data;
import net.einself.hingucker.databus.DataBus;
import net.einself.hingucker.member.Member;
import net.einself.hingucker.core.data.DomainDataResult;
import net.einself.hingucker.member.subdomaingatherer.handler.DomainHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class SubdomainGathererMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();
    private final DataBus dataBus;

    @Inject
    public SubdomainGathererMember(DataBus dataBus) {
        this.dataBus = dataBus;
    }

    @Override
    public void accept(Data data) {
        LOGGER.debug("Receiving data");

        if (data instanceof DomainDataResult) {
            final var domain = (DomainDataResult) data;
            LOGGER.debug("Resolves data as Subdomain data: {}", domain.getDomain());
            new DomainHandler(dataBus, domain).run();
        }
    }

}
