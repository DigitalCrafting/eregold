package org.digitalcrafting.arkenstone.repository.customers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomersMapper {
    CustomerEntity getByEmail(String email);

    CustomerEntity getByCustomerId(String customerId);

    void insert(CustomerEntity entity);
}
