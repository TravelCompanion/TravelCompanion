package common.data;

public interface UserConverter<T> {
	public T fromVirtualUser(VirtualUser virtualUser);
	public VirtualUser toVirtualUser();
}
