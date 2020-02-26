import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that implements the channel used by headquarters and space explorers to communicate.
 */
public class CommunicationChannel {
	private static ArrayBlockingQueue<Message> pairedMessage = new ArrayBlockingQueue<Message>(2);
	private static ArrayBlockingQueue<Message> explorerQueue = new ArrayBlockingQueue<Message>(1000);
	private static ArrayBlockingQueue<Message> hqQueue = new ArrayBlockingQueue<Message>(1000);

	private static ReentrantLock messagePair = new ReentrantLock();

	private static String END = "END";
	private static String EXIT = "EXIT";
	/**
	 * Creates a {@code CommunicationChannel} object.
	 */
	public CommunicationChannel() {
	}

	/**
	 * Puts a message on the space explorer channel (i.e., where space explorers write to and 
	 * headquarters read from).
	 * 
	 * @param message
	 *            message to be put on the channel
	 */
	public void putMessageSpaceExplorerChannel(Message message) {

		hqQueue.add(message);

	}

	/**
	 * Gets a message from the space explorer channel (i.e., where space explorers write to and
	 * headquarters read from).
	 * 
	 * @return message from the space explorer channel
	 */
	public Message getMessageSpaceExplorerChannel() {

		Message message = null;

		try {
			message = hqQueue.take();
		} catch (InterruptedException e) {}

		return message;

	}

	/**
	 * Puts a message on the headquarters channel (i.e., where headquarters write to and 
	 * space explorers read from).
	 * 
	 * @param message
	 *            message to be put on the channel
	 */
	public void putMessageHeadQuarterChannel(Message message) {

		// Closing the threads
		if (message.getData().equals(EXIT)) {
			explorerQueue.add(message);
		}

		// I don t realy care about who sends the messages
		if (message.getData().equals(END)) {
			return;
		}

		// I make sure that if 1 thread can create the massage pair
		// 1 thread must call this method 2 before any other thread can use it
		messagePair.lock();

		pairedMessage.add(message);

		// Creating a true message that contains all the information needed
		// Realeasing the lock and letting other thread access the method
		if (messagePair.getHoldCount() == 2) {

			// Creating a true message :))
			Message aux = pairedMessage.poll();
			Message trueMessage = pairedMessage.poll();
			trueMessage.setParentSolarSystem(aux.getCurrentSolarSystem());
			explorerQueue.add(trueMessage);

			for (int i = 0; i < 2; i++) {
				messagePair.unlock();
			}

		}

	}

	/**
	 * Gets a message from the headquarters channel (i.e., where headquarters write to and
	 * space explorer read from).
	 * 
	 * @return message from the header quarter channel
	 */
	public Message getMessageHeadQuarterChannel() {

		Message message = null;

		try {
			message = explorerQueue.take();
		} catch (InterruptedException e) {}

		return message;

	}
}
