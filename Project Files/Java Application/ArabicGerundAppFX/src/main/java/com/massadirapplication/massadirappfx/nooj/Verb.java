package com.massadirapplication.massadirappfx.nooj;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "verb")
@XmlType(propOrder = {"type", "lemma", "root", "pattern", "rootInfo", "transitivity", "form", "conjugation"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Verb {
    @XmlElement(name = "type")
    public String type;
    @XmlElement(name = "lemma")
    public String lemma;
    @XmlElement(name = "root")
    public String root;
    @XmlElement(name = "pattern")
    public String pattern;
    @XmlElement(name = "rootInfo")
    public String rootInfo;
    @XmlElement(name = "transitivity")
    public String transitivity;
    @XmlElement(name = "form")
    public String form;
    @XmlElement(name = "conjugation")
    public String conjugation;
}
