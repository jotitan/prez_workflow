package fr.titan.workflow;

import fr.titan.workflow.model.Ticket;
import fr.titan.workflow.rules.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class WorkflowRunner {
    private RulesEngine engine;
    private Rules rules;

    public WorkflowRunner(){
        RulesEngineParameters parameters = new RulesEngineParameters();
        parameters.setSkipOnFirstAppliedRule(true);
        this.engine = new DefaultRulesEngine(parameters);
        initRules();
    }

    public void assign(Ticket ticket, String user){
        genericExecute(ticket, user, null, null);
    }

    public void reject(Ticket ticket, String comment){
        genericExecute(ticket, null, false, comment);
    }

    public void submit(Ticket ticket){
        genericExecute(ticket, null, null, null);
    }

    public void managerSubmission(Ticket ticket, boolean validate){
        genericExecute(ticket, null, validate, null);
    }

    public void close(Ticket ticket){
        genericExecute(ticket,null,null, null);
    }

    public void validate(Ticket ticket){
        genericExecute(ticket, null, true,"");
    }

    private void genericExecute(Ticket ticket, String user, Boolean validate, String comment) {
        Facts facts = new Facts();
        facts.put("ticket", ticket);
        if(user != null) {
            facts.put("user", user);
        }
        if(validate != null) {
            facts.put("validate", validate);
        }
        if(comment != null) {
            facts.put("comment", comment);
        }
        this.engine.fire(this.rules, facts);
    }

    private void initRules() {
        this.rules = new Rules();
        rules.register(new DraftRule());
        rules.register(new WaitingManagerTicketRule());
        rules.register(new WaitingAssignmentTicketRule());
        rules.register(new WaitingActionTicketRule());
        rules.register(new WaitingCloseTicketRule());
    }

}
