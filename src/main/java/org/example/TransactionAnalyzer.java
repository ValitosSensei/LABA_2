package org.example;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public class TransactionAnalyzer {
    private final List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;
    public List<Transaction> findTopExpensesForPeriod(Date startDate, Date endDate, int count) {
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getDate().compareTo(String.valueOf(startDate)) >= 0 && transaction.getDate().compareTo(String.valueOf(endDate)) <= 0)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .limit(count)
                .collect(Collectors.toList());
        return filteredTransactions;
    }
    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }


}
