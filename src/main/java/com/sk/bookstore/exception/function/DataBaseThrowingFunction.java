/**
 * 
 */
package com.sk.bookstore.exception.function;

import java.util.function.Function;

import com.sk.bookstore.exception.DataBaseException;

/**
 * @author Surendra Kumar
 *
 */
@FunctionalInterface
public interface DataBaseThrowingFunction<T, R> extends Function<T, R> {

	@Override
	default R apply(T t) {
		R r = null;
		try {
			r = applyThrows(t);
		} catch (Exception e) {
			throw new DataBaseException(e);
		}
		return r;
	}

	R applyThrows(T t) throws DataBaseException;
}
