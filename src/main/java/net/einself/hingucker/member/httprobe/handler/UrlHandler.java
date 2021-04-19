package net.einself.hingucker.member.httprobe.handler;

import kong.unirest.Headers;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.message.HttpHeaderMessage;
import net.einself.hingucker.core.message.HttpResponseResultMessage;
import net.einself.hingucker.core.message.UrlMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UrlHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private final UrlMessage url;
    private final MessageBus messageBus;

    public UrlHandler(MessageBus messageBus, UrlMessage url) {
        this.messageBus = messageBus;
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
            messageBus.publish(httpResponse);

            httpResponse.getHeaders().forEach(messageBus::publish);

        } catch (UnirestException e) {
            LOGGER.debug("Cannot connect to {} via {} method", url.get(), method);
        }
    }

    private List<HttpHeaderMessage> createCreateHeaders(Headers headers) {
        return headers.all().stream()
                .map(header -> new HttpHeaderMessage(header.getName(), header.getValue()))
                .collect(toList());
    }

    private HttpResponseResultMessage createResponse(String method, Headers headers) {
        return new HttpResponseResultMessage(url.get(), method, createCreateHeaders(headers));
    }

}
