package common.convertion.generic;

import model.Utilisateur;

public interface UserConverter<T2> extends Converter<Utilisateur,T2> {
	/**classes witch define users data must implements this interface*/
	public T2 fromDatabase(Utilisateur data);
	public Utilisateur toDataBase(T2 data);
}
