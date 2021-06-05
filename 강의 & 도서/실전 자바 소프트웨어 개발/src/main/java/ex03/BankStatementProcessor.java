package ex03;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
	private static List<BankTransaction> bankTransactions;
	
	public BankStatementProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double calculateTotalAmount() {
		double total = 0d;
		
		for(BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}
	
	/* 특정 월에 해당하는 모든 내역 검색 */
	public static List<BankTransaction> calculateTotalInMonth(final Month month) {
		final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		
		for(BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}
		}
		return bankTransactionsInMonth;
	}
	
	/* 특정 카테고리에 해당하는 금액 총합 */
	public double calculateTotalForCategory(final String category) {
		double total = 0;
		for(BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}
	
	/* 특정 금액 이상의 모든 입출금 내역을 검색 */
	public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
		final List<BankTransaction> result = new ArrayList<>();
		
		for(BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getAmount() >= amount) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	/* 특정 월의 입출금 내역 찾기 --> 이미 중복된 코드 */
	public List<BankTransaction> findTransactionsInMonth(final Month month){
		final List<BankTransaction> result = new ArrayList<>();
		
		for(BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	/* 특정 월이나 특정 금액으로 입출금 내역 검색 */
	public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
	
	/*
	 * 함수형 인터페이스(BankTransactionFilter)를 구현(Predicate) 해서 특정 조건의 참, 거짓 여부를 판단하도록 매개변수를 받음.
	 * 새 인터페이스를 이용해 반복 로직과 비즈니스 로직의 결합을 제거하는 과정
	 * 위 findTransactionsInMonthAndGreater() 메서드를 리팩토링하고, 변경 없이도 확장성 개방되는 개방/폐새 원칙적용(OCP)
	 */
	public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
		final List<BankTransaction> result = new ArrayList<>();
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		return result;
	}
}
