package com.zcrmyy.equ.repository;

import com.zcrmyy.equ.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/28.
 */
@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
