package com.massadirapplication.massadirappfx.nooj;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "msd")
@XmlType(propOrder = {"omsd", "hmsd", "mamsd", "mimsd", "smsd"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Msd {
    @XmlElement(name = "omsd")
    public String omsd;
    @XmlElement(name = "hmsd")
    public String hmsd;
    @XmlElement(name = "mamsd")
    public String mamsd;
    @XmlElement(name = "mimsd")
    public String mimsd;
    @XmlElement(name = "smsd")
    public String smsd;


    public Msd() {
        this.omsd = new String();
        this.hmsd = new String();
        this.mamsd = new String();
        this.mimsd = new String();
        this.smsd = new String();
    }

    public Msd(String omsd, String hmsd, String mamsd, String mimsd, String smsd) {
        this.omsd = omsd;
        this.hmsd = hmsd;
        this.mamsd = mamsd;
        this.mimsd = mimsd;
        this.smsd = smsd;
    }
}
