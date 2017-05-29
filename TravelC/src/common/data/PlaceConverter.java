package common.data;

public interface PlaceConverter<T>{
	public T fromVirtualPlace(VirtualPlace virtualPlace);
	public VirtualPlace toVirtualPlace();

}
