package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WaitingCloseTicketRuleTest {
    private WaitingCloseTicketRule rule = new WaitingCloseTicketRule();;

    @Test
    void TicketMustBeClosable() {
        // THEN
        Assertions.assertTrue(rule.check(new Ticket(Status.REJECTED)));
        Assertions.assertTrue(rule.check(new Ticket(Status.VALIDATED)));
    }

    @Test
    void TicketWaitingAssignMustFail() {
        // THEN
        Assertions.assertFalse(rule.check(new Ticket(Status.WAITING_ASSIGNEMENT)));
    }

    @Test
    void TicketValidatedMustSuccess() {
        Ticket ticket = new Ticket(Status.VALIDATED);

        rule.action(ticket);
        Assertions.assertEquals(Status.CLOSED, ticket.getStatus());
    }

    @Test
    void TicketRejectedMustSuccess() {
        Ticket ticket = new Ticket(Status.REJECTED);

        rule.action(ticket);
        Assertions.assertEquals(Status.CLOSED, ticket.getStatus());
    }

}