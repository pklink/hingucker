package net.einself.sicherheitswerkzeug;

import com.beust.jcommander.JCommander;
import net.einself.sicherheitswerkzeug.core.data.DomainDataResult;
import net.einself.sicherheitswerkzeug.member.httprobe.HttpProbeMember;
import net.einself.sicherheitswerkzeug.member.output.OutputMember;
import net.einself.sicherheitswerkzeug.member.subdomaingatherer.SubdomainGathererMember;
import net.einself.sicherheitswerkzeug.member.url.UrlMember;

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
