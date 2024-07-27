package me.dio.service;

import me.dio.domain.model.Loan;

public interface LoanService {

    //Get Methods
    Loan getLoanById(Long id);

    Iterable<Loan> getAllLoans();

    Iterable<Loan> getAllPendentLoans();

    //Post Methods
    Loan saveLoan(Loan newLoan);

    //Put Methods
    Loan renewLoan(Long id);

    //Delete Methods
    Loan deleteLoan(Long id);
}
