/**
 * 
 */
package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.Payment;

/**
 * @author Surendra Kumar
 *
 */
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}

