package common.convertion.generic;

import model.Musee;

public interface MuseeConverter<T2> extends Converter<Musee, T2>{
	public T2 fromDatabase(Musee data);
	public Musee toDataBase(T2 data);
}
