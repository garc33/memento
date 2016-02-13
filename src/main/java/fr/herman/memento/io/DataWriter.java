package fr.herman.memento.io;

import java.io.DataOutput;

public interface DataWriter<T> {
	long write(T data, DataOutput output);
}
