package com.meugestor.services;

import com.meugestor.entities.Debt;
import com.meugestor.entities.Payment;
import com.meugestor.entities.Status;
import com.meugestor.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DebtService debtService;

    @Transactional
    public Payment createPayment(UUID debtId, BigDecimal amountPaid, LocalDate paymentDate) {
        Debt debt = debtService.getDebtById(debtId)
                .orElseThrow(() -> new RuntimeException("Debt not found"));

        if (amountPaid.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Payment amount must be positive");
        }

        // Check if payment exceeds debt amount
        if (amountPaid.compareTo(debt.getAmount()) > 0) {
            throw new RuntimeException("Payment amount cannot exceed the debt amount");
        }

        Payment newPayment = new Payment(debt, amountPaid, paymentDate);
        Payment savedPayment = paymentRepository.save(newPayment);

        // Update debt status if payment is total or greater
        BigDecimal remainingAmount = debt.getAmount().subtract(amountPaid);
        if (remainingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            debt.setStatus(Status.PAID);
            debtService.updateDebt(debt); // Assuming updateDebt method exists in DebtService
        }

        return savedPayment;
    }

    public List<Payment> getPaymentsByDebtId(UUID debtId) {
        // This would typically involve a custom query in the repository
        // For now, we'll return all payments and filter in memory (not ideal for large datasets)
        return debtRepository.findAll().stream() // Replace with proper repository method
                .filter(p -> p.getDebt().getId().equals(debtId))
                .toList();
    }

    public Optional<Payment> getPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    // Helper method to update debt, assuming it will be implemented in DebtService
    private void updateDebt(Debt debt) {
        debtService.updateDebt(debt); // This needs to be implemented in DebtService
    }
}
