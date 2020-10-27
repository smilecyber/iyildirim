package com.openpayd.iyildirim.repository;

import com.openpayd.iyildirim.entity.Address;
import com.openpayd.iyildirim.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
