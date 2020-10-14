package com.trustly.trustly.model;

public class File {
    private String fileName;
    private String extension;
    private String url;
    private Float size;
    private Integer lines;
    private String unidade;

    public File() {
    }

    public File(String fileName, String extension, String url, Float size, Integer lines, String unidade) {
        this.fileName = fileName;
        this.extension = extension;
        this.url = url;
        this.size = size;
        this.lines = lines;
        this.unidade = unidade;
    }
    

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        String array[] = fileName.split("\\.");
        setExtension(array[array.length-1]);
        this.fileName = fileName;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getSize() {
        return this.size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Integer getLines() {
        return this.lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public String getUnidade() {
        return this.unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
