package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Job {
    private String jobTitle;
    private String jobDescription;
    private JobLocationType jobLocationType;

    public Job(String jobTitle, String jobDescription, JobLocationType jobLocationType) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocationType = jobLocationType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public JobLocationType getJobLocationType() {
        return jobLocationType;
    }

    public void setJobLocationType(JobLocationType jobLocationType) {
        this.jobLocationType = jobLocationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobTitle.equals(job.jobTitle) && Objects.equals(jobDescription, job.jobDescription) && jobLocationType == job.jobLocationType;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocationType=" + jobLocationType +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobTitle, jobDescription, jobLocationType);
    }
}
