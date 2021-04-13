package net.einself.sicherheitswerkzeug.tools;

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
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class SubdomainGatherer {

    private final static Logger LOGGER = LogManager.getLogger();

    private final File nameListFile;

    public SubdomainGatherer(File nameListFile) {
        this.nameListFile = nameListFile;
    }

    public List<InetSocketAddress> run(String domain) throws UnknownHostException {
        return getNames(nameListFile).stream()
                .map(name -> name + "." + domain)
                .map(this::createResolver)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SimpleResolver::getAddress)
                .peek(inetSocketAddress -> LOGGER.info("Found {}", inetSocketAddress))
                .collect(toList());
    }

    private Optional<SimpleResolver> createResolver(String domain) {
        try {
            final var resolver = new SimpleResolver(domain);
            return Optional.of(resolver);
        } catch (UnknownHostException e) {
            return Optional.empty();
        }
    }

    private static List<String> getNames(File file) {
        try {
            return FileUtils.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
