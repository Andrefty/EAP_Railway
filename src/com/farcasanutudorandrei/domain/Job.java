package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Job {
    private int id_job;
    private String jobTitle;
    private String jobDescription;
    private JobLocationType jobLocationType;

    public Job(int id_job, String jobTitle, String jobDescription, JobLocationType jobLocationType) {
        this.id_job = id_job;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocationType = jobLocationType;
    }

    public int getId_job() {
        return id_job;
    }

    public void setId_job(int id_job) {
        this.id_job = id_job;
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
    public String toString() {
        return "Job{" +
                "id_job=" + id_job +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocationType=" + jobLocationType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id_job == job.id_job && jobTitle.equals(job.jobTitle) && jobDescription.equals(job.jobDescription) && jobLocationType == job.jobLocationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_job, jobTitle, jobDescription, jobLocationType);
    }
}
