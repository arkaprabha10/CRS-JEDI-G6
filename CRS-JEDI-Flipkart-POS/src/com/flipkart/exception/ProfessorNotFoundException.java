package com.flipkart.exception;

public class ProfessorNotFoundException extends Exception {

    private String professorID;

    public ProfessorNotFoundException() {
        this.professorID = "";
    }

    /**
     * @param professorID
     */
    public ProfessorNotFoundException(String professorID) {
        super();
        this.professorID = professorID;
    }

    /**
     * @return the courseID
     */
    public String professorID() {
        return professorID;
    }

    @Override
    public String getMessage() {
        return "ProfessorID: " + professorID + "is not present in the list!";
    }
}
