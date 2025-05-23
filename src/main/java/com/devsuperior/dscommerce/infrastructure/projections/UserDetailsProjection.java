package com.devsuperior.dscommerce.infrastructure.projections;

public interface UserDetailsProjection {
    String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();

}
