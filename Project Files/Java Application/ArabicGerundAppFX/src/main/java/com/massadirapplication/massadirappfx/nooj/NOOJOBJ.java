package com.massadirapplication.massadirappfx.nooj;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "NOOJOBJ")
@XmlType(propOrder = {"VMSD", "MSDV", "ANV"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NOOJOBJ {

    @XmlElement(name = "VMSD")
    public List<VMSD> VMSD;

    @XmlElement(name = "MSDV")
    public List<MSDV> MSDV;

    @XmlElement(name = "ANV")
    public List<ANV> ANV;

}
