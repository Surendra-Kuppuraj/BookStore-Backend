/**
 * 
 */
package com.sk.bookstore.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sk.bookstore.domain.security.Authority;
import com.sk.bookstore.domain.security.UserRole;

/**
 * @author Surendra Kumar
 *
 */

@Entity
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 902783495L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;

	private String email;
	private String phone;
	private boolean enabled = true;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserPayment> userPaymentList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserShipping> userShippingList;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "user")
	private ShoppingCart shoppingCart;

	@OneToMany(mappedBy="user")
	private List<Order> orderList;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public List<UserPayment> getUserPaymentList() {
		return userPaymentList;
	}

	public void setUserPaymentList(List<UserPayment> userPaymentList) {
		this.userPaymentList = userPaymentList;
	}

	public List<UserShipping> getUserShippingList() {
		return userShippingList;
	}

	public void setUserShippingList(List<UserShipping> userShippingList) {
		this.userShippingList = userShippingList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), isEnabled(), getFirstName(), getLastName(), getPassword(), getPhone(),
				getUsername(), getUserRoles());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return Boolean.TRUE;
		}
		if (obj instanceof User) {
			User user = (User) obj;
			return Objects.equals(email, user.email) && Objects.equals(enabled, user.enabled)
					&& Objects.equals(firstName, user.firstName) && Objects.equals(id, user.id)
					&& Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password)
					&& Objects.equals(phone, user.phone) && Objects.equals(userRoles, user.userRoles)
					&& Objects.equals(username, user.username);
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("User [id=").append(id).append(", username=").append(username).append(", password=")
				.append(password).append(", firstName=").append(firstName).append(", lastName=").append(lastName)
				.append(", email=").append(email).append(", phone=").append(phone).append(", enabled=").append(enabled)
				.append(", userRoles=").append(userRoles).append("]");
		return outputBuffer.toString();
	}

}
