package fr.titan.workflow;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowRunnerTest {
    private WorkflowRunner runner = new WorkflowRunner();

    @Test
    void whenTicketAreWaitingAssignementMustHaveAssigner() {
        // GIVEN
        Ticket ticket = new Ticket(Status.WAITING_ASSIGNEMENT);
        String userAssign = "User Assign";
        // WHEN
        runner.assign(ticket, userAssign);

        // THEN
        Assertions.assertEquals(userAssign, ticket.getAssignement());
        Assertions.assertEquals(Status.WAITING_ACTION, ticket.getStatus());

    }

    @Test
    void whenRunValidatedCompleteWorkflow(){
        // GIVEN
        Ticket ticket = new Ticket(Status.WAITING_ASSIGNEMENT);
        String userAssign = "User Assign";
        // WHEN
        runner.assign(ticket, userAssign);
        runner.validate(ticket);

        // THEN
        Assertions.assertEquals(userAssign, ticket.getAssignement());
        Assertions.assertEquals(Status.VALIDATED, ticket.getStatus());
    }

    @Test
    void whenRunRejectedCompleteWorkflow(){
        // GIVEN
        Ticket ticket = new Ticket(Status.WAITING_ASSIGNEMENT);
        String userAssign = "User Assign";
        // WHEN
        runner.assign(ticket, userAssign);
        runner.reject(ticket, "Bad request");

        // THEN
        Assertions.assertEquals(userAssign, ticket.getAssignement());
        Assertions.assertEquals(Status.REJECTED, ticket.getStatus());
    }

    @Test
    void whenRunCompleteWorkflow(){
        // GIVEN
        Ticket ticket = new Ticket(Status.WAITING_ASSIGNEMENT);
        String userAssign = "User Assign";
        // WHEN
        runner.assign(ticket, userAssign);
        runner.reject(ticket, "Demande incorrecte");
        runner.close(ticket);

        // THEN
        Assertions.assertEquals(Status.CLOSED, ticket.getStatus());
    }

    @Test
    void whenRunCompleteWorkflowFromDraft(){
        // GIVEN
        Ticket ticket = new Ticket();
        String userAssign = "User Assign";
        // WHEN
        runner.submit(ticket);
        runner.managerSubmission(ticket, true);
        runner.assign(ticket, userAssign);
        runner.reject(ticket, "Demande incorrecte");
        runner.close(ticket);

        // THEN
        Assertions.assertEquals(Status.CLOSED, ticket.getStatus());
    }

    @Test
    void whenDoubleRunCompleteWorkflowFromDraft(){
        // GIVEN
        Ticket ticket = new Ticket();
        String userAssign = "User Assign";
        // WHEN
        runner.submit(ticket);
        runner.managerSubmission(ticket, false);
        runner.submit(ticket);
        runner.managerSubmission(ticket, true);
        runner.assign(ticket, userAssign);
        runner.reject(ticket, "Demande incorrecte");
        runner.close(ticket);

        // THEN
        Assertions.assertEquals(Status.CLOSED, ticket.getStatus());
    }

}