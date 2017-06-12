package com.zcrmyy.equ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zcrmyy.equ.entity.Supplier;

/**
 * Created by Administrator on 2017/4/28.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
