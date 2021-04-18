package net.einself.hingucker.member.subdomaingatherer.handler;

import net.einself.hingucker.DataBus;
import net.einself.hingucker.core.data.DomainDataResult;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xbill.DNS.SimpleResolver;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class DomainHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DomainDataResult domain;
    private final File nameListFile;

    public DomainHandler(DomainDataResult domain) {
        this.domain = domain;
        final var namelistFilepath = System.getenv().get("NAMELIST_FILEPATH");
        this.nameListFile = new File(namelistFilepath);
    }

    private static List<String> getNames(File file) {
        try {
            return FileUtils.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void run() {
        final UnaryOperator<String> buildSubdomain = name -> name + "." + domain.getDomain();

        getNames(nameListFile).stream()
                .map(buildSubdomain)
                .map(this::createResolver)
                .filter(Objects::nonNull)
                .map(SimpleResolver::getAddress)
                .forEach(this::publish);
    }

    private void publish(InetSocketAddress inetSocketAddress) {
        DataBus.INSTANCE.publish(createDomain(inetSocketAddress));
    }

    private DomainDataResult createDomain(InetSocketAddress inetSocketAddress) {
        final var address = inetSocketAddress.getAddress();
        final var hostName = address.getHostName();
        LOGGER.info("Found domain {}", hostName);
        return new DomainDataResult(hostName);
    }

    private SimpleResolver createResolver(String domain) {
        try {
            return new SimpleResolver(domain);
        } catch (UnknownHostException e) {
            LOGGER.debug("Cannot resolve {}", domain);
            return null;
        }
    }

}
