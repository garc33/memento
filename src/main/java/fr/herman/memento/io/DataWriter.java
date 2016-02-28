package fr.herman.memento.io;

import java.io.DataOutput;
import java.io.IOException;

public interface DataWriter<T> {
	long write(T data, DataOutput output) throws IOException;
}
