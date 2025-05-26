package com.devsuperior.dscommerce.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscommerce.domain.entity.User;
import com.devsuperior.dscommerce.infrastructure.projections.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = """
            	SELECT app_user.email AS username, app_user.password, app_role.id AS roleId, app_role.authority
            	FROM app_user
            	INNER JOIN tb_user_role ON app_user.id = tb_user_role.user_id
            	INNER JOIN app_role ON app_role.id = tb_user_role.role_id
            	WHERE app_user.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

	Optional<User> findByEmail(String email);

}
