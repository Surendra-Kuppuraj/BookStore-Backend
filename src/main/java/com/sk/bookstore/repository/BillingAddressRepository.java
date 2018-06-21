/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.BillingAddress;

/**
 * @author Surendra Kumar
 *
 */
public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long>{

}
