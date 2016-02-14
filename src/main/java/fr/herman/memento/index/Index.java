package fr.herman.memento.index;

import java.io.IOException;

public interface Index {
	long getPosition(long id) throws IOException;

	void setPosition(long id, long position) throws IOException;
}
