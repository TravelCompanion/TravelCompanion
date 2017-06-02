package common.convertion.generic;

import model.Monument;

public interface MonumentConverter<T2> extends Converter<Monument, T2>{
	/**classes witch define places data must implements this interface*/
	public T2 fromDatabase(Monument data);
	public Monument toDataBase(T2 data);

}
