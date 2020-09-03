package com.adeleke.samad.gadsleaderboard.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submission {
    @SerializedName("entry.1877115667")
    @Expose
    private String firstName;

    @SerializedName("entry.2006916086")
    @Expose
    private String lastName;

    @SerializedName("entry.1824927963")
    @Expose
    private String email;

    @SerializedName("entry.284483984")
    @Expose
    private String projectLink;

    public Submission(String firstName, String lastName, String email, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projectLink = projectLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

}
