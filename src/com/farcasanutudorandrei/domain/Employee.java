package com.farcasanutudorandrei.domain;

import java.util.Date;
import java.util.Objects;

public class Employee extends Person {
    private double salary;
    private Date hireDate;
    private String phoneNumber;
    private Job job;
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 && hireDate.equals(employee.hireDate) && phoneNumber.equals(employee.phoneNumber) && job.equals(employee.job) && Objects.equals(department, employee.department);
    }

    @Override
    public String toString() {
        return "Employee{" +super.toString()+
                "salary=" + salary +
                ", hireDate=" + hireDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", job=" + job +
                ", department=" + department +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, hireDate, phoneNumber, job, department);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(String name, String firstName, String email, String CNP, double salary, Date hireDate, String phoneNumber, Job job, Department department) {
        super(name, firstName, email, CNP);
        this.salary = salary;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.department = department;
    }
}
