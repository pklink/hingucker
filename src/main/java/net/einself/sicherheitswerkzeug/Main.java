package net.einself.sicherheitswerkzeug;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.beust.jcommander.JCommander;
import net.einself.sicherheitswerkzeug.domain.Project;
import net.einself.sicherheitswerkzeug.domain.Subdomain;
import net.einself.sicherheitswerkzeug.observer.ResultLoggingObserver;
import net.einself.sicherheitswerkzeug.tools.subdomain.SubdomainGatherer;
import net.einself.sicherheitswerkzeug.tools.subdomain.SubdomainResult;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] argv) {
        final ProgramArguments args = createProgramArguments(argv);

        final Project project = createProject(args);
        final SubdomainGatherer subdomainGatherer = createSubdomainGatherer(args);
        subdomainGatherer.addObserver(result -> {
            final var subdomainResult = (SubdomainResult) result;
            final var hostName = subdomainResult.getHostName();
            final var hostAddress = subdomainResult.getHostAddress();
            final var fqdn = subdomainResult.getFqdn();
            final var subdomain = new Subdomain(hostName, hostAddress, fqdn);
            project.getSubdomains().add(subdomain);
        });

        subdomainGatherer.run(project.getTarget());

        writeResultFile(args, project);
    }

    private static void writeResultFile(ProgramArguments args, Project project) {
        final var json = JSON.toJSONString(project, SerializerFeature.PrettyFormat);

        try {
            FileUtils.writeStringToFile(args.getOutputFile(), json, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Cannot write output file {}; Content:\n{}", args.getOutputFile(), json, e);
        }
    }

    private static ProgramArguments createProgramArguments(String[] argv) {
        final var args = new ProgramArguments();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        return args;
    }

    private static Project createProject(ProgramArguments programArguments) {
        return new Project(programArguments.getTarget());
    }

    private static SubdomainGatherer createSubdomainGatherer(ProgramArguments programArguments) {
        final var subdomainGatherer = new SubdomainGatherer(programArguments.getNameListFile());
        subdomainGatherer.addObserver(new ResultLoggingObserver());
        return subdomainGatherer;
    }

}
