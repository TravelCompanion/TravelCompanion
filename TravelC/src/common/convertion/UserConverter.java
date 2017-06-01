package common.convertion;

import model.Utilisateur;

public interface UserConverter<T> {
	/**classes witch define users data must implements this interface*/
	public T fromDatabaselUser(Utilisateur virtualUser);
	public Utilisateur toDatabaseUser();
}
