package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WaitingActionRuleTest {
    private WaitingActionTicketRule rule = new WaitingActionTicketRule();;

    @Test
    void TicketRejectedMustBeReject() {
        // THEN
        Assertions.assertTrue(rule.check(new Ticket(Status.WAITING_ACTION),false, "Bad comment"));
        Assertions.assertTrue(rule.check(new Ticket(Status.WAITING_ACTION),true,""));
    }

    @Test
    void TicketWaitingAssignMustFail() {
        // THEN
        Assertions.assertFalse(rule.check(new Ticket(Status.WAITING_ASSIGNEMENT),false,""));
    }

    @Test
    void TicketRejectedMustFail() {
        // THEN
        Assertions.assertFalse(rule.check(new Ticket(Status.REJECTED),false,"Bad request"));
    }


    @Test
    void TicketValidatedMustSuccess() {
        Ticket ticket = new Ticket(Status.WAITING_ACTION);

        rule.action(ticket,true,"");
        Assertions.assertEquals(Status.VALIDATED, ticket.getStatus());
        Assertions.assertNull(ticket.getComment());
    }

    @Test
    void TicketRejectedMustSuccess() {
        Ticket ticket = new Ticket(Status.WAITING_ACTION);
        String comment = "Bad request";

        rule.action(ticket,false, comment);
        Assertions.assertEquals(Status.REJECTED, ticket.getStatus());
        Assertions.assertEquals(comment, ticket.getComment());
    }

}