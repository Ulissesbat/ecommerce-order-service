package com.lbea.pedidos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lbea.pedidos.entities.AuthUser;
import com.lbea.pedidos.projections.UserDetailsProjection;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
	
	Optional<AuthUser> findByEmail(String email);


	    @Query(nativeQuery = true, value = """
	        SELECT u.email AS username, 
	               u.password, 
	               r.id AS roleId, 
	               r.authority
	        FROM tb_auth_user u
	        INNER JOIN tb_auth_user_role ur ON u.id = ur.auth_user_id
	        INNER JOIN tb_role r ON r.id = ur.role_id
	        WHERE u.email = :email
	    """)
	    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
	}

	

