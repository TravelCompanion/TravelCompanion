package common.data;

public interface PlaceConverter<T>{
	/**classes witch define places data must implements this interface*/
	public T fromVirtualPlace(VirtualPlace virtualPlace);
	public VirtualPlace toVirtualPlace();

}
