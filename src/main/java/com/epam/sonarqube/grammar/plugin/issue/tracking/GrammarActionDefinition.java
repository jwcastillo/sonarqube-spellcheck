package com.epam.sonarqube.grammar.plugin.issue.tracking;

import org.sonar.api.ServerExtension;
import org.sonar.api.issue.action.Actions;
import org.sonar.api.issue.condition.HasIssuePropertyCondition;
import org.sonar.api.issue.condition.IsUnResolved;
import org.sonar.api.issue.condition.NotCondition;

import com.epam.sonarqube.grammar.plugin.PluginParameter;


public final class GrammarActionDefinition implements ServerExtension {

    private final Actions actions;
    private final AddWordFromIssueFunction addWordFromIssueFunction;

    public GrammarActionDefinition(Actions actions, AddWordFromIssueFunction addWordFromIssueFunction) {
        this.actions = actions;
        this.addWordFromIssueFunction = addWordFromIssueFunction;
    }

    public void start() {
        actions.add(PluginParameter.ADD_TO_DICT)
                .setConditions(
                        new NotCondition(new HasIssuePropertyCondition(PluginParameter.SONAR_GRAMMAR_ISSUE_DATA_PROPERTY_KEY)),
                        new IsUnResolved()
                )
                .setFunctions(addWordFromIssueFunction);
    }
}



