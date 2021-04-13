package net.einself.sicherheitswerkzeug;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import net.einself.sicherheitswerkzeug.domain.Project;
import net.einself.sicherheitswerkzeug.domain.Subdomain;
import net.einself.sicherheitswerkzeug.tools.SubdomainGatherer;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] argv) throws UnknownHostException {
        final var args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        final var project = new Project("heise.de");

        final var subdomainGatherer = new SubdomainGatherer(args.getNameListFile());
        final var addresses = subdomainGatherer.run("heise.de");

        addresses.stream()
                .map(InetSocketAddress::getAddress)
                .map(Subdomain::new)
                .forEach(subdomain -> project.getSubdomains().add(subdomain));


        final var json = JSON.toJSONString(project, SerializerFeature.PrettyFormat);

        try {
            FileUtils.writeStringToFile(args.outputFile, json, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Cannot write output file {}; Content:\n{}", args.getOutputFile(), json, e);
        }
    }



    static class Args {

        @Parameter(names = "--namelist")
        private File nameListFile;

        @Parameter(names = "--out")
        private File outputFile;

        public File getNameListFile() {
            return nameListFile;
        }

        public void setNameListFile(File nameListFile) {
            this.nameListFile = nameListFile;
        }

        public File getOutputFile() {
            return outputFile;
        }

        public void setOutputFile(File outputFile) {
            this.outputFile = outputFile;
        }
    }

}
