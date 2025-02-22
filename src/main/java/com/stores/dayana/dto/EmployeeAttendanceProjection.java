package com.stores.dayana.dto;

public interface EmployeeAttendanceProjection {

    Long getEmployeeId();
    String getFirstName();
    String getLastName();
    Integer getAttendanceCount();
    Integer getLeaveCount();

}
