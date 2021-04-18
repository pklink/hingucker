package net.einself.hingucker;

import com.beust.jcommander.JCommander;
import net.einself.hingucker.core.data.DomainDataResult;
import net.einself.hingucker.member.httprobe.HttpProbeMember;
import net.einself.hingucker.member.output.OutputMember;
import net.einself.hingucker.member.subdomaingatherer.SubdomainGathererMember;
import net.einself.hingucker.member.url.UrlMember;

public class Main {

    public static void main(String[] argv) {
        final ProgramArguments args = createProgramArguments(argv);

        DataBus.INSTANCE.subscribe(new SubdomainGathererMember());
        DataBus.INSTANCE.subscribe(new HttpProbeMember());
        DataBus.INSTANCE.subscribe(new UrlMember());

        DataBus.INSTANCE.subscribe(new OutputMember());

        DataBus.INSTANCE.publish(new DomainDataResult(args.getTarget()));
    }

    private static ProgramArguments createProgramArguments(String[] argv) {
        final var args = new ProgramArguments();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        return args;
    }

}
