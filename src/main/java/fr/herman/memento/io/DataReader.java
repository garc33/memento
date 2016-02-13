package fr.herman.memento.io;

import java.io.DataInput;

public interface DataReader<T> {
	T read(DataInput input);
}
