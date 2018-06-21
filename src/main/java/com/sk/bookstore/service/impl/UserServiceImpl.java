package com.sk.bookstore.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.bookstore.domain.ShoppingCart;
import com.sk.bookstore.domain.User;
import com.sk.bookstore.domain.UserBilling;
import com.sk.bookstore.domain.UserPayment;
import com.sk.bookstore.domain.UserShipping;
import com.sk.bookstore.domain.security.UserRole;
import com.sk.bookstore.exception.DataBaseException;
import com.sk.bookstore.exception.function.DataBaseThrowingFunction;
import com.sk.bookstore.repository.RoleRepository;
import com.sk.bookstore.repository.UserBillingRepository;
import com.sk.bookstore.repository.UserPaymentRepository;
import com.sk.bookstore.repository.UserRepository;
import com.sk.bookstore.repository.UserShippingRepository;
import com.sk.bookstore.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserBillingRepository userBillingRepository;

	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Autowired
	private UserShippingRepository userShippingRepository;

	private final BiFunction<User, Set<UserRole>, User> userFunction = (user, userRoles) -> {
		userRoles.stream().forEach(userRole -> roleRepository.save(userRole.getRole()));
		user.getUserRoles().addAll(userRoles);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		user.setShoppingCart(shoppingCart);
		Optional<User> optionalUser = userSave(user);
		return optionalUser.orElseThrow(() -> new DataBaseException("User cannot be created."));
	};

	@Override
	public User createUser(final User user, final Set<UserRole> userRoles) {
		Optional<User> optionalLocalUser = findByUserName(user.getUsername());
		optionalLocalUser.ifPresent(
				usr -> LOGGER.info("User with username {} already exist. Nothing will be done. ", usr.getUsername()));
		return optionalLocalUser.orElse(userFunction.apply(user, userRoles));
	}

	@Override
	public Optional<User> findByUserName(final String userName) {
		DataBaseThrowingFunction<String, Optional<User>> userDataBaseThrowingFunction = (findByUserName) -> {
			return Optional.ofNullable(userRepository.findByUsername(findByUserName));
		};
		return userDataBaseThrowingFunction.apply(userName);
	}

	@Override
	public Optional<User> findByEmail(final String email) {
		DataBaseThrowingFunction<String, Optional<User>> userDataBaseThrowingFunction = (findByEmail) -> {
			return Optional.ofNullable(userRepository.findByEmail(findByEmail));
		};
		return userDataBaseThrowingFunction.apply(email);
	}

	@Override
	public Optional<User> userSave(final User user) {
		DataBaseThrowingFunction<User, Optional<User>> userDataBaseThrowingFunction = (createUser) -> {
			return Optional.ofNullable(userRepository.save(createUser));
		};
		return userDataBaseThrowingFunction.apply(user);
	}

	@Override
	public Optional<User> findById(final Long id) {
		DataBaseThrowingFunction<Long, Optional<User>> userDataBaseThrowingFunction = (findById) -> {
			return Optional.ofNullable(userRepository.findOne(findById));
		};
		return userDataBaseThrowingFunction.apply(id);
	}

	@Override
	public void updateUserPaymentInfo(final UserBilling userBilling, final UserPayment userPayment, final User user) {
		Optional<User> updateUserPayment = userSave(user);
		if (updateUserPayment.isPresent()) {
			userBillingRepository.save(userBilling);
			userPaymentRepository.save(userPayment);
		}
	}

	@Override
	public void updateUserBilling(final User user, final UserPayment userPayment) {
		UserBilling userBilling = userPayment.getUserBilling();
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		userSave(user);
	}

	@Override
	public void setUserDefaultPayment(final Long userPaymentId, final User user) {
		DataBaseThrowingFunction<User, List<UserPayment>> dataBaseThrowingFunction = (findById) -> {
			return userPaymentRepository.findByUser(user);
		};
		List<UserPayment> listUserPayment = dataBaseThrowingFunction.apply(user);
		if(!listUserPayment.isEmpty()) {
			Supplier<Stream<UserPayment>> userPaymentSupplier = () -> Stream.of(listUserPayment)
					.flatMap(Collection::stream);
			userPaymentSupplier.get().filter(userPayment -> userPayment.getId() == userPaymentId)
					.forEach(userPayment -> updateDefaultUserPayment(userPayment, Boolean.TRUE));
			userPaymentSupplier.get().filter(userPayment -> userPayment.getId() != userPaymentId)
					.forEach(userPayment -> updateDefaultUserPayment (userPayment, Boolean.FALSE));
		}	
	}

	@Override
	public void updateUserShipping(final UserShipping userShipping, final User user) {
		userShipping.setUser(user);
		userShipping.setDefaultShipping(false);
		user.getUserShippingList().add(userShipping);
		userSave(user);
	}

	@Override
	public void setUserDefaultShipping(final Long userShippingId, final User user) {
		DataBaseThrowingFunction<User, List<UserShipping>> dataBaseThrowingFunction = (findById) -> {
			return userShippingRepository.findByUser(user);
		};
		List<UserShipping> listUserShipping = dataBaseThrowingFunction.apply(user);
		if(!listUserShipping.isEmpty()) {
			Supplier<Stream<UserShipping>> userShippingSupplier = () -> Stream.of(listUserShipping)
					.flatMap(Collection::stream);
			userShippingSupplier.get().filter(userShipping -> userShipping.getId() == userShippingId)
					.forEach(userShipping -> updateDefaultUserShipping(userShipping, Boolean.TRUE));
			userShippingSupplier.get().filter(userShipping -> userShipping.getId() != userShippingId)
					.forEach(userShipping -> updateDefaultUserShipping(userShipping, Boolean.FALSE));
		}
	}

	private void updateDefaultUserPayment(final UserPayment userPayment, final boolean paymentMode) {
		userPayment.setDefaultPayment(paymentMode);
		userPaymentRepository.save(userPayment);
	}
	private void updateDefaultUserShipping(final UserShipping userShipping, final boolean shippingMode) {
		userShipping.setDefaultShipping(shippingMode);
		userShippingRepository.save(userShipping);
	}
	

}
