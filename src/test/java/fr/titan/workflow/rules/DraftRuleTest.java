package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DraftRuleTest {
    private DraftRule rule = new DraftRule();;

    @Test
    void TicketMustValidate() {
        // THEN
        Assertions.assertTrue(rule.check(new Ticket(Status.DRAFT)));
    }

    @Test
    void TicketMustFail() {
        // THEN
        Assertions.assertFalse(rule.check(new Ticket(Status.WAITING_ASSIGNEMENT)));
    }

    @Test
    void TicketValidatedMustSuccess() {
        Ticket ticket = new Ticket(Status.DRAFT);

        rule.action(ticket);
        Assertions.assertEquals(Status.WAITING_MANAGER, ticket.getStatus());
    }
}