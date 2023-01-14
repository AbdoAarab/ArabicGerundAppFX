package com.massadirapplication.massadirappfx.nooj;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "MSDV")
@XmlType(propOrder = {"entry", "verb", "msd"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MSDV {

    @XmlElement(name = "entry")
    public String entry;

    @XmlElement(name = "verb")
    public Verb verb;

    @XmlElement(name = "msd")
    public Msd msd;
}
