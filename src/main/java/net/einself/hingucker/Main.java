package net.einself.hingucker;

import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import net.einself.hingucker.core.Arguments;
import net.einself.hingucker.core.message.DomainResultMessage;
import net.einself.hingucker.core.messagebus.MessageBus;
import net.einself.hingucker.core.messagebus.MessageBusModule;
import net.einself.hingucker.member.httprobe.HttpProbeMember;
import net.einself.hingucker.member.output.OutputMember;
import net.einself.hingucker.member.subdomaingatherer.SubdomainGathererMember;
import net.einself.hingucker.member.subdomaingatherer.SubdomainGathererMemberModule;
import net.einself.hingucker.member.url.UrlMember;
import net.einself.hingucker.member.url.UrlMemberModule;

public class Main {

    public static void main(String[] argv) {
        final Arguments args = createProgramArguments(argv);

        final var injector = Guice.createInjector(
                new MessageBusModule(),
                new SubdomainGathererMemberModule(),
                new UrlMemberModule()
        );

        final var messageBus = injector.getInstance(MessageBus.class);

        messageBus.subscribe(injector.getInstance(SubdomainGathererMember.class));
        messageBus.subscribe(injector.getInstance(HttpProbeMember.class));
        messageBus.subscribe(injector.getInstance(UrlMember.class));
        messageBus.subscribe(injector.getInstance(OutputMember.class));

        messageBus.publish(new DomainResultMessage(args.getTarget()));
    }

    private static Arguments createProgramArguments(String[] argv) {
        final var args = new Arguments();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        return args;
    }

}
