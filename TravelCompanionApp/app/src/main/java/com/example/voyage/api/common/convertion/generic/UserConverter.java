package com.example.voyage.api.common.convertion.generic;

import com.example.voyage.api.data.TheoricUser;
import com.example.voyage.travelcompanionapp.model.ApliUser;

public interface UserConverter<T2> extends Converter<ApliUser,T2> {
	/**classes witch define users data must implements this interface*/
	public T2 fromDatabase(ApliUser data);
	public ApliUser toDataBase(T2 data);
}
