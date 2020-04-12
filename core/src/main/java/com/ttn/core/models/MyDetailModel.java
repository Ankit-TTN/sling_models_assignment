package com.ttn.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyDetailModel {

    @Inject
    private String firstName;

    @Inject
    private String lastName;

    @Inject
    private Date dob;

    @Inject
    private String gender;

    @Inject
    private String maritalStatus;

    private int age;

    private String honorific;

    private String fullName;

    @PostConstruct
    protected void init() {
        fullName = "";
        fullName += firstName != null ? firstName : "";
        fullName += lastName != null ? " " + lastName : "";

        if (gender != null) {
            if (gender.equals("male")) {
                honorific = "Mr";
            } else {
                honorific = maritalStatus.equals("single") ? "Ms" : "Mrs";
            }
        }

        if (dob != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dob);
            LocalDate extractedDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            age = Period.between(extractedDate, LocalDate.now()).getYears();
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getMartialStatus() {
        return maritalStatus;
    }

    public int getAge() {
        return age;
    }

    public String getHonorific() {
        return honorific;
    }
}
