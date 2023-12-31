package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule
public class WaitingCloseTicketRule {

    @Condition
    public boolean check(@Fact("ticket") Ticket ticket
    ){
        return ticket.getStatus() == Status.REJECTED || ticket.getStatus() == Status.VALIDATED;
    }

    @Action
    public void action(@Fact("ticket") Ticket ticket){
        ticket.setStatus(Status.CLOSED);
    }

}
