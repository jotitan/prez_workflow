package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule
public class WaitingActionTicketRule {

    @Condition
    public boolean check(@Fact("ticket") Ticket ticket,
                         @Fact("validate") Boolean validate,
                         @Fact("comment")String comment){
        return ticket.getStatus() == Status.WAITING_ACTION && (validate || !"".equals(comment));
    }

    @Action
    public void action(@Fact("ticket") Ticket ticket,
                       @Fact("validate")Boolean validate,
                       @Fact("comment")String comment){
        ticket.setStatus(validate ? Status.VALIDATED : Status.REJECTED);
        if(!validate){
            ticket.setComment(comment);
        }
    }

}
