package ex02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public BankTransaction parseFromCSV(final String line) {
		final String[] columns = line.split(",");
		
		final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		final double amount = Double.parseDouble(columns[1]);
		final String description = columns[2];
		
		return new BankTransaction(date, amount, description);
	}
	
	public List<BankTransaction> parseLinesFromCSV(final List<String> lines){
		final List<BankTransaction> bankTransactions = new ArrayList<>();
		
		for(final String line: lines) {
			bankTransactions.add(parseFromCSV(line));
		}
		return bankTransactions;
	}
}
