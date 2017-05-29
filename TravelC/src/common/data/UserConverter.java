package common.data;

public interface UserConverter<T> {
	/**classes witch define users data must implements this interface*/
	public T fromVirtualUser(VirtualUser virtualUser);
	public VirtualUser toVirtualUser();
}
