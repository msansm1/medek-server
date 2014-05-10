package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Loan;

/**
 * DAO for LOAN table
 * 
 * @author msansm1
 *
 */
@Stateless
public class LoanDAO extends Dao {

	public void updateLoan(Loan loan) {
		em.merge(loan);
	}

	public void saveLoan(Loan loan) {
		em.persist(loan);
		em.refresh(loan);
	}

	public void removeLoan(Loan loan) {
		em.remove(em.merge(loan));
		em.flush();
	}

	public List<Loan> getLoans() {
		return em.createQuery("from Loan", Loan.class)
				.getResultList();
	}

	public Loan getLoan(Integer id) {
		return em.find(Loan.class, id);
	}
}
