package ex05;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BusinessRuleEngineTest {
	
	@Test
	public void shouldHaveNoRulesInitially() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		
		assertEquals(1, businessRuleEngine.count());
	}
	
	@Test
	public void shouldAddTwoActions() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		
		businessRuleEngine.addAction(() -> {});
		businessRuleEngine.addAction(() -> {});
		
		assertEquals(2, businessRuleEngine.count());
	}
	
	@Test
	public void shouldExecuteOneAction() {
		final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
		final Action mockAction = mock(Action.class);
		
		businessRuleEngine.addAction(mockAction);
		businessRuleEngine.run();
		
		verify(mockAction).execute();
	}
}
