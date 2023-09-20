package fr.titan.workflow.rules;

import fr.titan.workflow.model.Status;
import fr.titan.workflow.model.Ticket;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule
public class WaitingAssignmentTicketRule {

    @Condition
    public boolean check(@Fact("ticket") Ticket ticket,
                         @Fact("user")String user
    ){
        return ticket.getStatus() == Status.WAITING_ASSIGNEMENT;
    }

    @Action
    public void action(@Fact("ticket") Ticket ticket,
                       @Fact("user")String user){
        ticket.setAssignement(user);
        ticket.setStatus(Status.WAITING_ACTION);

    }

}
