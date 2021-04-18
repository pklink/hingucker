package net.einself.hingucker.core;

import com.beust.jcommander.Parameter;

import java.io.File;

public class Arguments {

    @Parameter(names = "--namelist")
    private File nameListFile;

    @Parameter(names = "--out")
    private File outputFile;

    @Parameter(names = "--target")
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

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
