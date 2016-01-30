package fr.herman.memento.id;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator<UUID> {

	@Override
	public UUID nextId() {
		return UUID.randomUUID();
	}

}
