package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReportGenerator {
    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }
    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
    public void printExpenseReport(List<Transaction> transactions) {
        // Створюємо карту категорій та суми витрат
        Map<String, Double> categoryExpenses = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        // Виводимо заголовок
        System.out.println("Сумарні витрати по категоріям:");

        // Виводимо сумарні витрати по категоріях
        categoryExpenses.forEach((category, totalAmount) -> System.out.println(category + ": " + totalAmount));

        // Створюємо карту місяців та суми витрат
        Map<String, Double> monthExpenses = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getMonthYear, Collectors.summingDouble(Transaction::getAmount)));

        // Виводимо заголовок
        System.out.println("\nСумарні витрати по місяцях:");

        // Виводимо сумарні витрати по місяцях
        monthExpenses.forEach((month, totalAmount) -> System.out.println(month + ": " + totalAmount));
    }
}
