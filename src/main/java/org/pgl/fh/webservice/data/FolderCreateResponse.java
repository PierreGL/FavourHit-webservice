package org.pgl.fh.webservice.data;

import java.util.HashSet;
import java.util.Set;

public class FolderCreateResponse {

	private Boolean creationSucceed;

	private Set<CreateFailCause> createFailCauseSet = new HashSet<>();
	
	public Boolean getCreationSucceed() {
		return creationSucceed;
	}
	public void setCreationSucceed(Boolean creationSucceed) {
		this.creationSucceed = creationSucceed;
	}
	public Set<CreateFailCause> getCreateFailCauseSet() {
		return createFailCauseSet;
	}
	public void addCreateFailCause(CreateFailCause createFailCause) {
		this.createFailCauseSet.add(createFailCause);
	}
}
