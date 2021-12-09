package org.digitalcrafting.eregold.repository.users;

import org.apache.ibatis.annotations.Mapper;

// TODO introduce EntityManager
@Mapper
public interface UsersMapper {
    UserEntity getByUserId(String userId);

    void insert(UserEntity entity);
}
