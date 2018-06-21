/**
 * 
 */
package com.sk.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Surendra Kumar
 *
 */
@Entity
public class ShippingAddress implements Serializable{
	
	private static final long serialVersionUID = 189013457L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String shippingAddressName;
	private String shippingAddressStreet1;
	private String shippingAddressStreet2;
	private String shippingAddressCity;
	private String shippingAddressState;
	private String shippingAddressCountry;
	private String shippingAddressZipcode;
	
	@OneToOne
	@JsonIgnore
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShippingAddressName() {
		return shippingAddressName;
	}

	public void setShippingAddressName(String shippingAddressName) {
		this.shippingAddressName = shippingAddressName;
	}

	public String getShippingAddressStreet1() {
		return shippingAddressStreet1;
	}

	public void setShippingAddressStreet1(String shippingAddressStreet1) {
		this.shippingAddressStreet1 = shippingAddressStreet1;
	}

	public String getShippingAddressStreet2() {
		return shippingAddressStreet2;
	}

	public void setShippingAddressStreet2(String shippingAddressStreet2) {
		this.shippingAddressStreet2 = shippingAddressStreet2;
	}

	public String getShippingAddressCity() {
		return shippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressState() {
		return shippingAddressState;
	}

	public void setShippingAddressState(String shippingAddressState) {
		this.shippingAddressState = shippingAddressState;
	}

	public String getShippingAddressCountry() {
		return shippingAddressCountry;
	}

	public void setShippingAddressCountry(String shippingAddressCountry) {
		this.shippingAddressCountry = shippingAddressCountry;
	}

	public String getShippingAddressZipcode() {
		return shippingAddressZipcode;
	}

	public void setShippingAddressZipcode(String shippingAddressZipcode) {
		this.shippingAddressZipcode = shippingAddressZipcode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((shippingAddressCity == null) ? 0 : shippingAddressCity.hashCode());
		result = prime * result + ((shippingAddressCountry == null) ? 0 : shippingAddressCountry.hashCode());
		result = prime * result + ((shippingAddressName == null) ? 0 : shippingAddressName.hashCode());
		result = prime * result + ((shippingAddressState == null) ? 0 : shippingAddressState.hashCode());
		result = prime * result + ((shippingAddressStreet1 == null) ? 0 : shippingAddressStreet1.hashCode());
		result = prime * result + ((shippingAddressStreet2 == null) ? 0 : shippingAddressStreet2.hashCode());
		result = prime * result + ((shippingAddressZipcode == null) ? 0 : shippingAddressZipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingAddress other = (ShippingAddress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (shippingAddressCity == null) {
			if (other.shippingAddressCity != null)
				return false;
		} else if (!shippingAddressCity.equals(other.shippingAddressCity))
			return false;
		if (shippingAddressCountry == null) {
			if (other.shippingAddressCountry != null)
				return false;
		} else if (!shippingAddressCountry.equals(other.shippingAddressCountry))
			return false;
		if (shippingAddressName == null) {
			if (other.shippingAddressName != null)
				return false;
		} else if (!shippingAddressName.equals(other.shippingAddressName))
			return false;
		if (shippingAddressState == null) {
			if (other.shippingAddressState != null)
				return false;
		} else if (!shippingAddressState.equals(other.shippingAddressState))
			return false;
		if (shippingAddressStreet1 == null) {
			if (other.shippingAddressStreet1 != null)
				return false;
		} else if (!shippingAddressStreet1.equals(other.shippingAddressStreet1))
			return false;
		if (shippingAddressStreet2 == null) {
			if (other.shippingAddressStreet2 != null)
				return false;
		} else if (!shippingAddressStreet2.equals(other.shippingAddressStreet2))
			return false;
		if (shippingAddressZipcode == null) {
			if (other.shippingAddressZipcode != null)
				return false;
		} else if (!shippingAddressZipcode.equals(other.shippingAddressZipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", shippingAddressName=" + shippingAddressName
				+ ", shippingAddressStreet1=" + shippingAddressStreet1 + ", shippingAddressStreet2="
				+ shippingAddressStreet2 + ", shippingAddressCity=" + shippingAddressCity + ", shippingAddressState="
				+ shippingAddressState + ", shippingAddressCountry=" + shippingAddressCountry
				+ ", shippingAddressZipcode=" + shippingAddressZipcode + ", order=" + order + "]";
	}
	
	
}

