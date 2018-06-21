/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.ShippingAddress;

/**
 * @author Surendra Kumar
 *
 */
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
	
}
