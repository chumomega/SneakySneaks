package com.example.sneakysneaks.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.sneakysneaks.objects.Sneaker;


@Repository
public class JdbcSneakerRepository implements SneakerRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String SQL_BRAND_SEARCH = "select * from sneakers where BRAND = ? ";
	private final String SQL_SHOW_SNEAKERS = "select * from sneakers ";
	private final String SQL_FIND_SNEAKERS = "select * from sneakers where product_number = ? ";
	private final String SQL_DELETE_SNEAKER = "delete from sneakers where product_number = ? ";
	private final String SQL_INSERT_SNEAKER = "insert into sneakers values (NULL, ?, ?, ?, ?)";
	
	
	
	@Override
	public Iterable<Sneaker> getSneakers(){
		return jdbcTemplate.query(SQL_SHOW_SNEAKERS, this::mapRowToResponse);
	}

	@Override
	public Iterable<Sneaker> findBrand(String brand) {
		return jdbcTemplate.query(SQL_BRAND_SEARCH, this::mapRowToResponse, brand);
	}

	@Override
	public Iterable<Sneaker> findSneaker(int id) {
		return jdbcTemplate.query(SQL_FIND_SNEAKERS, this::mapRowToResponse, id);
	}

	@Override
	public boolean addSneaker(String brand, String name, int size, double price, String about, String picture) {
		int numAffectedRows = jdbcTemplate.update(SQL_INSERT_SNEAKER, brand, name, size, price, about, picture );
		if(numAffectedRows > 0) {
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean removeSneaker(int id) {
		int numAffectedRows = jdbcTemplate.update(SQL_DELETE_SNEAKER, id);
		if(numAffectedRows > 0) {
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	private Sneaker mapRowToResponse(ResultSet rs, int rowNum) throws SQLException{	
		return new Sneaker(							
						rs.getInt("product_number"), 
						rs.getString("brand"), 
						rs.getString("name"),
						rs.getInt("size"),
						rs.getDouble("price"),
						rs.getString("about"),
						rs.getString("picture")
						);
		}
}





