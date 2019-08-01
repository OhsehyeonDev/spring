package dao.mapper;

import org.apache.ibatis.annotations.Insert;

import logic.SaleItem;

public interface SaleItemMapper {

	@Insert("insert into saleitem "
			+ "(saleid, saleitemid, itemid, quantity )"
			+ " values (#{saleId}, #{saleItemId}, #{item.id}, #{quantity})")
	void insert(SaleItem si);
	
}
