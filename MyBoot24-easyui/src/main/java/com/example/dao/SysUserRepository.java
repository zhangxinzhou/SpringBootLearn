package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long>{

}
