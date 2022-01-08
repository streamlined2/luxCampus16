package org.training.campus.onlineshop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.training.campus.onlineshop.model.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rowSet, int rowNum) throws SQLException {
		return Product.builder().
				id(rowSet.getLong("id")).
				name(rowSet.getString("name"))	.
				price(rowSet.getBigDecimal("price")).
				creationDate(rowSet.getDate("creation_date").toLocalDate()).
				build();
	}

}
