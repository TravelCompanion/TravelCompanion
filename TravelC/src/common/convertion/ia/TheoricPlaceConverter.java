package common.convertion.ia;

import api.data.TheoricPlace;
import common.convertion.generic.Converter;

public interface TheoricPlaceConverter<T2> extends Converter<TheoricPlace, T2>{
	/**classes witch define places data must implements this interface*/
	public T2 convertTo(TheoricPlace data);
	public TheoricPlace convertFrom(T2 data);
}
