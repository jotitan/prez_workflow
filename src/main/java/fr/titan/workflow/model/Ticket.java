package fr.titan.workflow.model;

public class Ticket {
    private String assignement;
    private String comment;
    private Status status;

    public Ticket(){
        this.status = Status.DRAFT;
    }

    public Ticket(Status status) {
        this.status = status;
    }

    public String getAssignement() {
        return assignement;
    }

    public void setAssignement(String assignement) {
        this.assignement = assignement;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
