package Account;

public class YouthAccount extends Account {
	private User supervisor;
	
	public YouthAccount(User owner, User supervisor) {
		super(owner, AccountType.youthAccount);
		this.supervisor = supervisor;
	}

	public User getSupervisor() {
		return supervisor;
	}
}