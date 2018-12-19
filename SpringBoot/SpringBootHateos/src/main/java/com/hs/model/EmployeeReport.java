package com.hs.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="employee-report")
public class EmployeeReport extends ResourceSupport implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    //You can add field as needed
}