/**
 * 
 */
package com.sk.bookstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Surendra Kumar
 *
 */
@Entity
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = -891273432L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private BigDecimal GrandTotal;

	@OneToMany(mappedBy = "shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItem> cartItemList;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getGrandTotal() {
		return GrandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		GrandTotal = grandTotal;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((GrandTotal == null) ? 0 : GrandTotal.hashCode());
		result = prime * result + ((cartItemList == null) ? 0 : cartItemList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (GrandTotal == null) {
			if (other.GrandTotal != null)
				return false;
		} else if (!GrandTotal.equals(other.GrandTotal))
			return false;
		if (cartItemList == null) {
			if (other.cartItemList != null)
				return false;
		} else if (!cartItemList.equals(other.cartItemList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", GrandTotal=" + GrandTotal + ", cartItemList=" + cartItemList + ", user="
				+ user + "]";
	}

}
