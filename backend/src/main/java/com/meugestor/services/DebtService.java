package com.meugestor.services;

import com.meugestor.entities.Debt;
import com.meugestor.entities.Status;
import com.meugestor.entities.User;
import com.meugestor.repositories.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DebtService {

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private UserService userService;

    public Debt createDebt(BigDecimal amount, LocalDate dueDate, UUID creditorId, UUID debtorId, Status status, BigDecimal interestRate) {
        User creditor = userService.getUserById(creditorId);
        User debtor = userService.getUserById(debtorId);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Due date cannot be in the past");
        }

        Debt newDebt = new Debt(amount, dueDate, creditor, debtor, status, interestRate);
        return debtRepository.save(newDebt);
    }

    public Debt updateDebt(Debt debt) {
        // Ensure the debt exists before updating
        if (!debtRepository.existsById(debt.getId())) {
            throw new RuntimeException("Debt not found");
        }
        return debtRepository.save(debt);
    }

    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    public Optional<Debt> getDebtById(UUID id) {
        return debtRepository.findById(id);
    }

    public void deleteDebt(UUID id) {
        debtRepository.deleteById(id);
    }
}
