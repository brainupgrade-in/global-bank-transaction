package in.brainupgrade.transactionservice.models;

public class ErrorDetails {
	private String message;
	private String details;

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public String getMessage() {
		return this.message;
	}

	public String getDetails() {
		return this.details;
	}

	public ErrorDetails() {
	}

	public ErrorDetails(final String message, final String details) {
		this.message = message;
		this.details = details;
	}
}
