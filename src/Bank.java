
public interface Bank {
	/**     
	 * Increase the value of the account by the specified amount.     
	 * If the account specified does not exist, assume the balance starts at zero.     
	 * @param account The account whose balance should be increased     
	 * @param amount The amount to increase the account’s balance by     
	 * @return The new balance of the account     
	 */    
	double deposit(Account account, double amount);    
	
	/**     
	 * Decrease the value of the account by the specified amount.     
	 * If the account specified does not exist, assume the balance starts at zero.     
	 * @param account The account whose balance should be decreased     
	 * @param amount The amount to decrease the account’s balance by     
	 * @return The new balance of the account     
	 */    
	double withdraw(Account account, double amount); 
}