package org.pgl.fh.webservice.data;

import java.util.HashSet;
import java.util.Set;

public class FolderRemoveResponse {

	private Boolean removingSucceed;

	private Set<RemoveFailCause> removeFailCausesSet = new HashSet<>();
	
	public void setRemovingSucceed(Boolean removingSucceed) {
		this.removingSucceed = removingSucceed;
	}
	
	public boolean isSucceed() {
		return removingSucceed != null && removingSucceed;
	}
	
	public Set<RemoveFailCause> getRemoveFailCausesSet() {
		return removeFailCausesSet;
	}

	public void addRemoveFailCause(RemoveFailCause removeFailCause) {
		removeFailCausesSet.add(removeFailCause);
	}
}
