package net.einself.hingucker.member.subdomaingatherer.handler;

import net.einself.hingucker.core.MessageHandler;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.message.DomainResultMessage;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xbill.DNS.SimpleResolver;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class DomainResultMessageHandler implements MessageHandler<DomainResultMessage> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final File nameListFile;
    private final MessageBus messageBus;

    @Inject
    public DomainResultMessageHandler(MessageBus messageBus) {
        this.messageBus = messageBus;
        this.nameListFile = new File(System.getenv().get("NAMELIST_FILEPATH"));
    }

    private static List<String> getNames(File file) {
        try {
            return FileUtils.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void accept(DomainResultMessage message) {
        final UnaryOperator<String> buildSubdomain = name -> name + "." + message.getDomain();

        getNames(nameListFile).stream()
                .map(buildSubdomain)
                .map(this::createResolver)
                .filter(Objects::nonNull)
                .map(SimpleResolver::getAddress)
                .forEach(this::publish);
    }

    private void publish(InetSocketAddress inetSocketAddress) {
        messageBus.publish(createDomain(inetSocketAddress));
    }

    private DomainResultMessage createDomain(InetSocketAddress inetSocketAddress) {
        final var address = inetSocketAddress.getAddress();
        final var hostName = address.getHostName();
        LOGGER.info("Found domain {}", hostName);
        return new DomainResultMessage(hostName);
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
