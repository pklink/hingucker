package net.einself.hingucker.member.output;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.einself.hingucker.core.message.Message;
import net.einself.hingucker.core.message.ResultMessage;
import net.einself.hingucker.core.Member;
import net.einself.hingucker.member.output.domain.Project;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class OutputMember implements Member {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Project project;
    private final File outputFile;

    public OutputMember() {
        project = new Project("adasd");
        final var outputFilepath = System.getenv("OUTPUT_FILEPATH");
        outputFile = new File(outputFilepath);
    }

    @Override
    public void accept(Message message) {
        if (message instanceof ResultMessage) {
            final var result = (ResultMessage) message;
            final var name = result.getClass().getSimpleName();

            final var results = project.getResults().getOrDefault(name, new ArrayList<>());
            results.add(result);
            project.getResults().put(name, results);

            writeResultFile();
        }
    }


    private void writeResultFile() {
        final var json = JSON.toJSONString(project, SerializerFeature.PrettyFormat);

        try {
            FileUtils.writeStringToFile(outputFile, json, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Cannot write output file {}; Content:\n{}", outputFile, json, e);
        }
    }

}
