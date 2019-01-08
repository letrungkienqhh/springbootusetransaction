package vn.hue.kienletrung.springboot.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vn.hue.kienletrung.springboot.model.BankAccountInfo;

public class BankAccountMapper implements RowMapper<BankAccountInfo> {
	public static final String Base_SQL="Select b.Id,b.Fullname,b.Balance from Bank_Account b";

	@Override
	public BankAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id=rs.getLong("Id");
			String fullName=rs.getString("Fullname");
			double balance=rs.getDouble("Balance");
		return new BankAccountInfo(id,fullName,balance);
	}
	

}
