package com.example.voyage.api.common.convertion.ia;

import com.example.voyage.api.api.data.TheoricUser;
import com.example.voyage.api.common.convertion.generic.Converter;

public interface TheoricUserConverter<T2> extends Converter<TheoricUser,T2> {
	/**classes witch define users data must implements this interface*/
	public T2 convertTo(TheoricUser data);
	public TheoricUser convertFrom(T2 data);
}
