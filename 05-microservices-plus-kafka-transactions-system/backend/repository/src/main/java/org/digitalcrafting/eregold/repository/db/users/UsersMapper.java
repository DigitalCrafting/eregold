package org.digitalcrafting.eregold.repository.db.users;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    UserEntity getByUserId(String userId);

    void insert(UserEntity entity);
}
