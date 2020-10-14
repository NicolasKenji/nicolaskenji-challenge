package com.trustly.trustly.model;

public class FileGroup {
    private String extension;
    private Integer totalNumberLines;
    private Double totalNumberbytes;

    public FileGroup() {
    }

    public FileGroup(String extension, Integer totalNumberLines, Double totalNumberbytes) {
        this.extension = extension;
        this.totalNumberLines = totalNumberLines;
        this.totalNumberbytes = totalNumberbytes;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getTotalNumberLines() {
        return this.totalNumberLines;
    }

    public void setTotalNumberLines(Integer totalNumberLines) {
        this.totalNumberLines = totalNumberLines;
    }

    public Double getTotalNumberbytes() {
        return this.totalNumberbytes;
    }

    public void setTotalNumberbytes(Double totalNumberbytes) {
        this.totalNumberbytes = totalNumberbytes;
    }
}
