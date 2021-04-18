package net.einself.sicherheitswerkzeug.member.httprobe.handler;

import kong.unirest.Headers;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import net.einself.sicherheitswerkzeug.DataBus;
import net.einself.sicherheitswerkzeug.core.data.HttpHeaderData;
import net.einself.sicherheitswerkzeug.core.data.HttpResponseDataResult;
import net.einself.sicherheitswerkzeug.core.data.UrlData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UrlHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UrlData url;

    public UrlHandler(UrlData url) {
        this.url = url;
    }

    public void run() {
        run("GET");
        run("POST");
        run("PUT");
        run("DELETE");
        run("HEAD");
        run("OPTION");
    }

    private void run(String method) {
        LOGGER.debug("Probe HTTP {} against {}", method, url.get());

        try {
            final var response = Unirest.request(method, url.get()).asString();
            LOGGER.info("Response with {} and a body size of {}", response.getStatus(), response.getBody().length());

            final var httpResponse = createResponse(method, response.getHeaders());
            DataBus.INSTANCE.publish(httpResponse);

            httpResponse.getHeaders().forEach(DataBus.INSTANCE::publish);

        } catch (UnirestException e) {
            LOGGER.debug("Cannot connect to {} via {} method", url.get(), method);
        }
    }

    private List<HttpHeaderData> createCreateHeaders(Headers headers) {
        return headers.all().stream()
                .map(header -> new HttpHeaderData(header.getName(), header.getValue()))
                .collect(toList());
    }

    private HttpResponseDataResult createResponse(String method, Headers headers) {
        return new HttpResponseDataResult(url.get(), method, createCreateHeaders(headers));
    }

}
