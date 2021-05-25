package ex02;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

public class BankStatementCSVParserTest {
	
	private final BankStatementParser statementParser = new BankStatementCSVParser();
	
	@Test
	public void shouldParseOneCorrectLine() throws Exception {
		final String line = "30-01-2017,-40,Testco";
		
		final BankTransaction result = statementParser.parseFromCSV(line);
		
		final BankTransaction expected = 
				new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Testco");
		
		final double tolerance = 9.0d;
		
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}
}
