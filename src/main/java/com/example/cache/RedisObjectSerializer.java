package com.example.cache;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisObjectSerializer implements RedisSerializer<Object>{

	static final byte[] EMPTY_ARRAY = new byte[0];
	
	private Converter<Object,byte[]> serializer = new SerializingConverter();
    private Converter<byte[],Object> deserializer = new DeserializingConverter();
	
	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		
		if(isEmpty(bytes)){
            return null;
        }
        try {
            return deserializer.convert(bytes);
        }catch (Exception e){
            throw new SerializationException("Can not deserializer", e);
        }
	}

	@Override
	public byte[] serialize(Object o) throws SerializationException {
		
		if(o == null){
            return EMPTY_ARRAY;
        }

        try {
            return serializer.convert(o);
        }catch (Exception e){
            return EMPTY_ARRAY;
        }
	}

	private boolean isEmpty(byte[] data){

        return (data == null || data.length == 0);
    }
}
