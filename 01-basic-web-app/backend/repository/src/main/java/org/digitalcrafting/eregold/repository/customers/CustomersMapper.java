package org.digitalcrafting.eregold.repository.customers;

import org.apache.ibatis.annotations.Mapper;

// TODO introduce EntityManager
@Mapper
public interface CustomersMapper {
    CustomerEntity getByEmail(String email);

    CustomerEntity getByCustomerId(String customerId);

    void insert(CustomerEntity entity);
}
