package com.example.voyage.api.common.convertion.generic;

import com.example.voyage.api.model.Monument;
import com.example.voyage.travelcompanionapp.model.ApliMonument;

public interface MonumentConverter<T2> extends Converter<ApliMonument, T2>{
	/**classes witch define places data must implements this interface*/
	public T2 fromDatabase(ApliMonument data);
	public ApliMonument toDataBase(T2 data);

}
