package net.einself.hingucker;

import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import net.einself.hingucker.core.data.DomainDataResult;
import net.einself.hingucker.databus.DataBus;
import net.einself.hingucker.databus.DataBusModule;
import net.einself.hingucker.member.httprobe.HttpProbeMember;
import net.einself.hingucker.member.output.OutputMember;
import net.einself.hingucker.member.subdomaingatherer.SubdomainGathererMember;
import net.einself.hingucker.member.url.UrlMember;

public class Main {

    public static void main(String[] argv) {
        final ProgramArguments args = createProgramArguments(argv);

        final var injector = Guice.createInjector(
                new DataBusModule()
        );

        final var dataBus = injector.getInstance(DataBus.class);

        dataBus.subscribe(injector.getInstance(SubdomainGathererMember.class));
        dataBus.subscribe(injector.getInstance(HttpProbeMember.class));
        dataBus.subscribe(injector.getInstance(UrlMember.class));
        dataBus.subscribe(injector.getInstance(OutputMember.class));

        dataBus.publish(new DomainDataResult(args.getTarget()));
    }

    private static ProgramArguments createProgramArguments(String[] argv) {
        final var args = new ProgramArguments();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        return args;
    }

}
