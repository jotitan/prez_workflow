package fr.titan.workflow.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

@Rule
public class WaitingManagerTicketRule {

    @Condition
    public boolean check() {
        return false;
    }

    @Action
    public void action() {
    }

}
