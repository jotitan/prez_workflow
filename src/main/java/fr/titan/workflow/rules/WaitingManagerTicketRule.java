package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule
public class WaitingManagerTicketRule {

    @Condition
    public boolean check(@Fact("ticket") Ticket ticket,
                         @Fact("validate") Boolean validate){
        return ticket.getStatus() == Status.WAITING_MANAGER;
    }

    @Action
    public void action(@Fact("ticket") Ticket ticket,
                       @Fact("validate")Boolean validate
                       ){
        ticket.setStatus(validate ? Status.WAITING_ASSIGNEMENT : Status.DRAFT);
    }

}
