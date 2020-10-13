package com.trustly.trustly.model;
import com.google.gson.Gson;
import java.util.Objects;

public class ClassFileGroup {
    private String extension;
    private Integer totalNumberLines;
    private Double totalNumberbytes;

    public ClassFileGroup() {
    }

    public ClassFileGroup(String extension, Integer totalNumberLines, Double totalNumberbytes) {
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

    public ClassFileGroup extension(String extension) {
        this.extension = extension;
        return this;
    }

    public ClassFileGroup totalNumberLines(Integer totalNumberLines) {
        this.totalNumberLines = totalNumberLines;
        return this;
    }

    public ClassFileGroup totalNumberbytes(Double totalNumberbytes) {
        this.totalNumberbytes = totalNumberbytes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClassFileGroup)) {
            return false;
        }
        ClassFileGroup classFileGroup = (ClassFileGroup) o;
        return Objects.equals(extension, classFileGroup.extension) && Objects.equals(totalNumberLines, classFileGroup.totalNumberLines) && Objects.equals(totalNumberbytes, classFileGroup.totalNumberbytes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extension, totalNumberLines, totalNumberbytes);
    }

    @Override
    public String toString() {
        return "{" +
            " extension='" + getExtension() + "'" +
            ", totalNumberLines='" + getTotalNumberLines() + "'" +
            ", totalNumberbytes='" + getTotalNumberbytes() + "'" +
            "}";
    }
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
