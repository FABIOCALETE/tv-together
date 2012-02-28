package com.snda.mzang.tvtogether.server.util;

import java.util.UUID;

public enum EntryId {

	USER("USER");

	String prefix;

	EntryId(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Use the random UUID to auto generate ID,
	 * 
	 * @return generated ID
	 */
	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		return prefix + (uuid.toString());
	}
}
