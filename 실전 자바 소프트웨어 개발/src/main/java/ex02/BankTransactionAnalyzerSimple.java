package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
 * 아래 코드에서 발생할 수 있는 문제
 *  1. 파일이 비어 있다면?
 *  2. 데이터에 문제가 있어서 금액을 파싱하지 못한다면?
 *  3. 행의 데이터가 완벽하지 않다면? 
 */
public class BankTransactionAnalyzerSimple {
	private static final String RESOURCES = "src/main/resources";
	
	public static void main(String[] args) throws IOException {
		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);
		double total = 0d;
		
		for(final String line: lines) {
			final String[] columns = line.split(",");
			final double amount = Double.parseDouble(columns[1]);
			total += amount;
		}
		
		System.out.println("The total for all transactions is " + total);
	}
}
