package vn.hue.kienletrung.springboot.DAO;

import java.util.List;

import javax.sql.DataSource;

import vn.hue.kienletrung.springboot.Exception.BankException;
import vn.hue.kienletrung.springboot.mapper.BankAccountMapper;
import vn.hue.kienletrung.springboot.model.BankAccountInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {
	@Autowired
	public BankAccountDAO(DataSource datasource) {
		this.setDataSource(datasource);
	}

	public List<BankAccountInfo> getBankAccount() {
		String sql = BankAccountMapper.Base_SQL;
		Object[] para = new Object[] {};
		BankAccountMapper mapper = new BankAccountMapper();
		List<BankAccountInfo> list = this.getJdbcTemplate().query(sql, para, mapper);
		return list;

	}

	public BankAccountInfo findBankAccount(long id) {
		String sql = BankAccountMapper.Base_SQL + " where b.Id=?";
		Object[] para = new Object[] { id };
		BankAccountMapper mapper = new BankAccountMapper();
		try {
			BankAccountInfo bank = (BankAccountInfo) this.getJdbcTemplate().queryForObject(sql, para, mapper);
			return bank;
		} catch (Exception e) {
			return null;

		}
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(long id, double ammout) throws BankException {
		BankAccountInfo account = this.findBankAccount(id);
		if (account == null) {
			throw new BankException("Account not found" + id);

		}
		double newbalance = account.getBalance() + ammout;
		if (newbalance < 0) {
			throw new BankException("You're have enough money: " + ammout);
		}
		account.setBalance(newbalance);
		String sql = "update BANK_ACCOUNT set Balance=? where Id=?";
		this.getJdbcTemplate().update(sql, newbalance, id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankException.class)
	public void sendMoney(long fromId, long toId, double ammount) throws BankException {

		try {
			addAmount(fromId, -ammount);
			addAmount(toId, ammount);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
