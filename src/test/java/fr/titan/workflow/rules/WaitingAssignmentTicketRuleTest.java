package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WaitingAssignmentTicketRuleTest {

    @Test
    void TicketWaitingAssignementShouldRule() {
        WaitingAssignmentTicketRule rule = new WaitingAssignmentTicketRule();

        Assertions.assertTrue(rule.check(new Ticket(Status.WAITING_ASSIGNEMENT),""));
    }

    @Test
    void TicketRejectedShouldNotRule() {
        WaitingAssignmentTicketRule rule = new WaitingAssignmentTicketRule();

        Assertions.assertFalse(rule.check(new Ticket(Status.REJECTED),""));
    }

}