package com.bryant.aot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bryant.aot.entity.AotMember;

public interface AotMemberDao extends JpaRepository<AotMember , Long>{

	@Query(value="select count(1) from AotMember m where m.username=:username")
	public Integer findByUsername(@Param("username") String username);
}
