package org.training.campus.onlineshop.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.training.campus.onlineshop.dao.ProductDao;
import org.training.campus.onlineshop.model.Product;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcTemplateProductDao implements ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final RowMapper<Product> rowMapper = new ProductRowMapper();

	private static final String SCHEMA = "\"onlineshop\"";
	private static final String TABLE = "product";

	private static final String FETCH_ALL_STATEMENT = String
			.format("select id, name, price, creation_date from %s.%s order by name asc", SCHEMA, TABLE);
	private static final String FETCH_ONE_STATEMENT = String
			.format("select id, name, price, creation_date from %s.%s where id=?", SCHEMA, TABLE);
	private static final String INSERT_ENTITY_STATEMENT = String
			.format("insert into %s.%s (name, price, creation_date) values(?,?,?)", SCHEMA, TABLE);
	private static final String UPDATE_ENTITY_STATEMENT = String
			.format("update %s.%s set name=?, price=?, creation_date=? where id=?", SCHEMA, TABLE);
	private static final String DELETE_ENTITY_STATEMENT = String.format("delete from %s.%s where id=?", SCHEMA, TABLE);

	@Override
	public List<Product> getAll() {
		return jdbcTemplate.query(FETCH_ALL_STATEMENT, rowMapper);
	}

	@Override
	public Optional<Product> findById(long id) {
		return Optional.of(jdbcTemplate.queryForObject(FETCH_ONE_STATEMENT, rowMapper, id));
	}

	@Override
	public void persist(Product product) {
		jdbcTemplate.update(INSERT_ENTITY_STATEMENT, product.getName(), product.getPrice(), product.getCreationDate());
	}

	@Override
	public void merge(Product product) {
		jdbcTemplate.update(UPDATE_ENTITY_STATEMENT, product.getName(), product.getPrice(), product.getCreationDate(),
				product.getId());
	}

	@Override
	public void remove(Product product) {
		remove(product.getId());
	}

	@Override
	public void remove(long id) {
		jdbcTemplate.update(DELETE_ENTITY_STATEMENT, id);
	}

}
