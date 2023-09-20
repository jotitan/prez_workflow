package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WaitingManagerTicketRuleTest {
    private WaitingManagerTicketRule rule = new WaitingManagerTicketRule();;

    @Test
    void TicketWaitingManagerMustManageable() {
        // THEN
        Assertions.assertTrue(rule.check(new Ticket(Status.WAITING_MANAGER),false));
        Assertions.assertTrue(rule.check(new Ticket(Status.WAITING_MANAGER),true));
    }

    @Test
    void TicketWaitingAssignMustFail() {
        // THEN
        Assertions.assertFalse(rule.check(new Ticket(Status.WAITING_ASSIGNEMENT),false));
    }

    @Test
    void TicketValidatedMustSuccess() {
        Ticket ticket = new Ticket(Status.WAITING_MANAGER);

        rule.action(ticket,true);
        Assertions.assertEquals(Status.WAITING_ASSIGNEMENT, ticket.getStatus());
    }

    @Test
    void TicketRejectedMustSuccess() {
        Ticket ticket = new Ticket(Status.WAITING_MANAGER);

        rule.action(ticket,false);
        Assertions.assertEquals(Status.DRAFT, ticket.getStatus());
    }

}