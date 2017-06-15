package com.example.voyage.api.common.convertion.generic;


public interface Converter<T1,T2> {
	public T2 convertTo(T1 data);
	public T1 convertFrom(T2 data);

}
