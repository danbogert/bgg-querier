package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Rating {

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private String value;
    @JacksonXmlProperty(localName = "average")
    private Average average;
    @JacksonXmlProperty(localName = "bayesaverage")
    private Average bayesianAverage;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Average getAverage() {
        return average;
    }

    public void setAverage(Average average) {
        this.average = average;
    }

    public Average getBayesianAverage() {
        return bayesianAverage;
    }

    public void setBayesianAverage(Average bayesianAverage) {
        this.bayesianAverage = bayesianAverage;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "value='" + value + '\'' +
                ", average=" + average +
                ", bayesianAverage=" + bayesianAverage +
                '}';
    }
}
