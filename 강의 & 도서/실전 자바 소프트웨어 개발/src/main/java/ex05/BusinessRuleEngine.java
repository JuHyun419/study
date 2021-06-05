package ex05;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {
	
	private final List<Action> actions;
	
	public BusinessRuleEngine() {
		this.actions = new ArrayList<>();
	}
	
	public List<Action> getActions() {
		return actions;
	}


	public void addAction(final Action action) {
		this.actions.add(action);
	}
	
	public int count() {
		return this.actions.size();
	}
	
	public void run() {
		this.actions.forEach(Action::execute);
	}
}
