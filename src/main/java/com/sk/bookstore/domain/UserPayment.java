/**
 * 
 */
package com.sk.bookstore.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Surendra Kumar
 *
 */
@Entity
public class UserPayment implements Serializable {

	private static final long serialVersionUID = 78091345L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String cardName;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	private String holderName;
	private boolean defaultPayment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
	private UserBilling userBilling;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserBilling getUserBilling() {
		return userBilling;
	}

	public void setUserBilling(UserBilling userBilling) {
		this.userBilling = userBilling;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + cvc;
		result = prime * result + (defaultPayment ? 1231 : 1237);
		result = prime * result + expiryMonth;
		result = prime * result + expiryYear;
		result = prime * result + ((holderName == null) ? 0 : holderName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userBilling == null) ? 0 : userBilling.hashCode());
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
		UserPayment other = (UserPayment) obj;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cvc != other.cvc)
			return false;
		if (defaultPayment != other.defaultPayment)
			return false;
		if (expiryMonth != other.expiryMonth)
			return false;
		if (expiryYear != other.expiryYear)
			return false;
		if (holderName == null) {
			if (other.holderName != null)
				return false;
		} else if (!holderName.equals(other.holderName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userBilling == null) {
			if (other.userBilling != null)
				return false;
		} else if (!userBilling.equals(other.userBilling))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", type=" + type + ", cardName=" + cardName + ", cardNumber=" + cardNumber
				+ ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear + ", cvc=" + cvc + ", holderName="
				+ holderName + ", defaultPayment=" + defaultPayment + ", user=" + user + ", userBilling=" + userBilling
				+ "]";
	}

}
