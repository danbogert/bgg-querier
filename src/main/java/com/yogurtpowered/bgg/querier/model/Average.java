package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Average {

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Average{" +
                "value=" + value +
                '}';
    }
}
