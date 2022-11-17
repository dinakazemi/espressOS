
/**
 * EspressOS Mobile Phone Contact Class.
 *
 * EspressOSContact
 * 
 * == Contact data ==
 * Each EspressOSContact stores the first name, last name and phone number of a person. 
 * These can be queried by calling the appropriate get method. They are updated 
 * with new values. The phone number can only be a 6 - 14 digit number.
 * The chat history is also stored. 
 * 
 * 
 * == Chat history ==
 * Each EspressOSContact stores the history of chat messages related to this contact. 
 * Suppose there is a conversation between Angus and Beatrice:
 * 
 * Angus: Man, I'm so hungry! Can you buy me a burrito?
 * Beatrice: I don't have any money to buy you a burrito.
 * Angus: Please? I haven't eaten anything all day.
 * 
 * Each time a message is added the name of the person and the message is 
 * combined as above and recorded in the sequence it was received.
 * 
 * The messages are stored in the instance variable String array chatHistory. Provided for you.
 * Unfortunately there are only 20 messages maximum to store and no more. 
 * When there are more than 20 messages, oldest messages from this array are discarded and 
 * only the most recent 20 messages remain. 
 * 
 * The functions for chat history are 
 *   addChatMessage
 *   getLastMessage
 *   getOldestMessage
 *   clearChatHistory()
 *
 * Using the above conversation as an example
 *   addChatMessage("Angus", "Man, I'm so hungry! Can you buy me a burrito?");
 *   addChatMessage("Beatrice", "I don't have any money to buy you a burrito.");
 *   addChatMessage("Angus", "Please? I haven't eaten anything all day.");
 *
 *   getLastMessage() returns "Angus: Please? I haven't eaten anything all day."
 *   getOldestMessage() returns "Angus: Man, I'm so hungry! Can you buy me a burrito?"
 *
 *   clearChatHistory()
 *   getLastMessage() returns null
 *   getOldestMessage() returns null
 *
 *
 * == Copy of contact ==
 * It is necessary to make copy of this object that contains exactly the same data. 
 * There are many hackers working in other parts of EspressOS, so we cannot trust them 
 * changing the data. A copy will have all the private data and chat history included.
 *
 *
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 *
 */
public class EspressOSContact
{
	public static final int MAXIMUM_CHAT_HISTORY = 20;	
	
	/* given */
	protected String[] chatHistory;
	private int chat_history_pointer; //I can't keep track of the chatHistory without a pointer?!
	private int oldest_message_pointer;
	private String fname;//first name
	private String lname; //last name
	private String pnumber; //phone number
	
	
	public EspressOSContact(String fname, String lname, String pnumber) {
		/* given */
		//initilaising the attributes 
		chatHistory = new String[MAXIMUM_CHAT_HISTORY];
		this.fname = fname;
		this.lname = lname;
		if (pnumber.length()>=6 && pnumber.length()<=14){
			this.pnumber = pnumber;
		}
		chat_history_pointer = 0;
		
	}
	
	public String getFirstName() {
		
		return this.fname;

	}
	public String getLastName() {
		
		return this.lname;

	}
	public String getPhoneNumber() {
		
		return this.pnumber;

	}

	/* if firstName is null the method will do nothing and return
	 */
	public void updateFirstName(String firstName) {
		
		//checks if the first name given is null
		if (firstName == null){
			return;
		}
		
		//sets the old first name to the new one
		this.fname = firstName;

	}
	/* if lastName is null the method will do nothing and return
	 */
	public void updateLastName(String lastName) {
		
		//checks if the last name given is null
		if (lastName == null){
			return;
		}
		
		//sets the old last name to the new one
		this.lname = lastName;

	}
	
	/* only allows integer numbers (long type) between 6 and 14 digits
	 * no spaces allowed, or prefixes of + symbols
	 * leading 0 digits are allowed
	 * return true if successfully updated
	 * if number is null, number is set to an empty string and the method returns false
	 */
	public boolean updatePhoneNumber(String number) {
		
		//is the given number null?
		if (number==null){
			
			//System.out.println("hi");
			this.pnumber = "";
			return false;
			
		}
		
		//boolean has_correct_foramt;
		
		//Does the given number have the correct length?
		if (number.length()<6 || number.length()>14){ 
			
			return false;
		}
		
		//doea the given number has space or + in it?
		for (int i=0;i<number.length();i++){
			
			char digit = number.charAt(i);
			
			if (digit == ' ' || digit == '+'){
				
				return false;
				
			}
			
			//is the given character an int?
			if (Character.isDigit(digit) == false){
				
				return false;
				
			}
			
		}
		
		//Finally! the format given is correct, so update the contact's number! 
		this.pnumber = number;
		return true;

	}
	
	/* add a new message to the chat
	 * The message will take the form
	 * whoSaidIt + ": " + message
	 * 
	 * if the history is full, the oldest message is replaced
	 * Hint: keep track of the position of the oldest or newest message!
	 */
	public void addChatMessage(String whoSaidIt, String message) {
		
		//System.out.println("chat_history_pointer: "+ chat_history_pointer);
		//is chat history for this contact full?
		if (chat_history_pointer%MAXIMUM_CHAT_HISTORY == 0 && chat_history_pointer!=0){
			
			oldest_message_pointer++;
			oldest_message_pointer%=MAXIMUM_CHAT_HISTORY;
			
			
		}
		
		// if there is still sapce, increment the pointer and add a new message to the chat history. 	
		chatHistory[chat_history_pointer%MAXIMUM_CHAT_HISTORY] = whoSaidIt + ": " + message;
		chat_history_pointer++;

	}

	/* after this, both last and oldest message should be referring to index 0
	 * all entries of chatHistory are set to null
	 */
	public void clearChatHistory() {
		
		//set the pointer equal to 0
		chat_history_pointer = 0;
		
		//all entries are now null.
		for (int i=0;i<chatHistory.length;i++){
			
			chatHistory[i] = null;
			
		}

	}

	/* returns the last message 
	 * if no messages, returns null
	 */
	public String getLastMessage() {
		
		//if the first chat is null, we have no messages 
		// if (chatHistory[0] == null){
		// 	return null;
		// }
		
		//otherwise, return the last message 
		int null_msg_count = 0;
		for (String s:chatHistory){
			if (s == null){
				null_msg_count++;
			}
		}
		if (null_msg_count == MAXIMUM_CHAT_HISTORY){
			return null;
		}
		return chatHistory[(chat_history_pointer-1)%MAXIMUM_CHAT_HISTORY];

	}
	
	/* returns the oldest message in the chat history
	 * depending on if there was ever MAXIMUM_CHAT_HISTORY messages
	 * 1) less than MAXIMUM_CHAT_HISTORY, returns the first message
	 * 2) more than MAXIMUM_CHAT_HISTORY, returns the oldest 
	 * returns null if no messages exist
	 */
	public String getOldestMessage() {
		
		return chatHistory[oldest_message_pointer];
	}


	/* creates a copy of this contact
	 * returns a new EspressOSContact object with all data same as the current object
	 */
	public EspressOSContact copy() 
	{
		
		EspressOSContact contact_copy = new EspressOSContact(this.fname,this.lname,this.pnumber);
		for (int i=0;i<MAXIMUM_CHAT_HISTORY;i++){
			contact_copy.chatHistory[i] = this.chatHistory[i];
		}
		contact_copy.oldest_message_pointer = this.oldest_message_pointer;
		contact_copy.chat_history_pointer = this.chat_history_pointer;
		return contact_copy;

	}
	
	/* -- NOT TESTED --
	 * You can impelement this to help with debugging when failing ed tests 
	 * involving chat history. You can print whatever you like
	 * Implementers notes: the format is printf("%d %s\n", index, line); 
	 */
	public void printMessagesOldestToNewest() {
		int counter =0 ;
		int index = 0;
		int oldest_message_copy = oldest_message_pointer;
		while(counter<10){
			//System.out.println("inedx: " + index);
			//System.out.println("oldest_message_pointer: "+ oldest_message_pointer);
			if ((oldest_message_copy+index)>=MAXIMUM_CHAT_HISTORY){
				index = 0;
				oldest_message_copy = 0;
			}
			//System.out.println(chatHistory[oldest_message_copy+index]);
			index ++ ;
			counter++;
		}
		// System.out.println();
		// for (int i=0;i<MAXIMUM_CHAT_HISTORY;i++){
		// 	System.out.println(chatHistory[i]);
		// }
	}
}
