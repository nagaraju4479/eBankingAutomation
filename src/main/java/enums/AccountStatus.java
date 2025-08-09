package enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
	
	NEW_REQUEST("New Request"),
    APPROVED("Approved"),
    REJECTED("Rejected");
	
	private final String status;

	AccountStatus(String status) {
		this.status = status;
	}
	
 
}
