package common.convertion;

import model.Monument;

public interface PlaceConverter<T>{
	/**classes witch define places data must implements this interface*/
	public T fromDatabasePlace(Monument virtualPlace);
	public Monument toVirtualPlace();

}
