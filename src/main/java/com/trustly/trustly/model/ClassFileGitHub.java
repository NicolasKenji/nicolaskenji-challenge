package com.trustly.trustly.model;

import java.util.Objects;

public class ClassFileGitHub {
    private String fileName;
    private String extension;
    private String url;
    private Float size;
    private Integer lines;
    private String unidade;

    public ClassFileGitHub() {
    }

    public ClassFileGitHub(String fileName, String extension, String url, Float size, Integer lines, String unidade) {
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

    public ClassFileGitHub fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ClassFileGitHub extension(String extension) {
        this.extension = extension;
        return this;
    }

    public ClassFileGitHub url(String url) {
        this.url = url;
        return this;
    }

    public ClassFileGitHub size(Float size) {
        this.size = size;
        return this;
    }

    public ClassFileGitHub lines(Integer lines) {
        this.lines = lines;
        return this;
    }

    public ClassFileGitHub unidade(String unidade) {
        this.unidade = unidade;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClassFileGitHub)) {
            return false;
        }
        ClassFileGitHub classFileGitHub = (ClassFileGitHub) o;
        return Objects.equals(fileName, classFileGitHub.fileName) && Objects.equals(extension, classFileGitHub.extension) && Objects.equals(url, classFileGitHub.url) && Objects.equals(size, classFileGitHub.size) && Objects.equals(lines, classFileGitHub.lines) && Objects.equals(unidade, classFileGitHub.unidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, extension, url, size, lines, unidade);
    }

    @Override
    public String toString() {
        return "{" +
            " fileName='" + getFileName() + "'" +
            ", extension='" + getExtension() + "'" +
            ", url='" + getUrl() + "'" +
            ", size='" + getSize() + "'" +
            ", lines='" + getLines() + "'" +
            ", unidade='" + getUnidade() + "'" +
            "}";
    }


}
