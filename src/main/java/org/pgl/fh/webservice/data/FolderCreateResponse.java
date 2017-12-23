package org.pgl.fh.webservice.data;

import java.util.HashSet;
import java.util.Set;

public class FolderCreateResponse {

	private Boolean creationSucceed;

	private Set<FolderCreateFailCause> createFailCauseSet = new HashSet<>();
	
	public Boolean getCreationSucceed() {
		return creationSucceed;
	}
	public void setCreationSucceed(Boolean creationSucceed) {
		this.creationSucceed = creationSucceed;
	}
	public Set<FolderCreateFailCause> getCreateFailCauseSet() {
		return createFailCauseSet;
	}
	public void addCreateFailCause(FolderCreateFailCause createFailCause) {
		this.createFailCauseSet.add(createFailCause);
	}
}
