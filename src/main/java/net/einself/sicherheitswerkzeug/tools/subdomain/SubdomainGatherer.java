package net.einself.sicherheitswerkzeug.tools.subdomain;

import net.einself.sicherheitswerkzeug.observer.ResultObserver;
import org.apache.commons.io.FileUtils;
import org.xbill.DNS.SimpleResolver;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SubdomainGatherer {

    private final File nameListFile;
    private final List<ResultObserver> observers = new ArrayList<>();

    public SubdomainGatherer(File nameListFile) {
        this.nameListFile = nameListFile;
    }

    public void run(String domain) {
        getNames(nameListFile).parallelStream()
                .map(name -> name + "." + domain)
                .map(this::createResolver)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SimpleResolver::getAddress)
                .map(this::createResult)
                .forEach(this::notify);
    }

    private SubdomainResult createResult(InetSocketAddress inetSocketAddress) {
        final var address = inetSocketAddress.getAddress();
        final var hostName = address.getHostName();
        final var hostAddress = address.getHostAddress();
        final var fqdn = address.getCanonicalHostName();
        return new SubdomainResult(hostName, hostAddress, fqdn);
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

    private void notify(SubdomainResult result) {
        observers.forEach(observer -> observer.update(result));
    }

    public void addObserver(ResultObserver observer) {
        observers.add(observer);
    }

}
