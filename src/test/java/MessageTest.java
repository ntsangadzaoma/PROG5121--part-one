import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for the Part 2 Message class. */
public class MessageTest {

    @BeforeEach
    void reset() {
        Message.resetMessages();
    }

    @Test
    void testMessageIdIsTenDigits() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    void testMessageIdTooLongFails() {
        Message message = new Message("12345678901", 0, "+27718693002", "Hello");
        assertFalse(message.checkMessageID());
    }

    @Test
    void testValidRecipient() {
        Message message = new Message("0000000000", 0, "+27718693002", "Hello");
        assertEquals("Recipient successfully captured.", message.checkRecipientCell());
    }

    @Test
    void testInvalidRecipient() {
        Message message = new Message("0000000000", 0, "08575975889", "Hello");
        assertEquals("Recipient cell number is incorrectly formatted.", message.checkRecipientCell());
    }

    @Test
    void testMessageAtOrBelow250CharactersIsReady() {
        String text = "a".repeat(250);
        Message message = new Message("0000000000", 0, "+27718693002", text);
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    @Test
    void testMessageOver250CharactersFails() {
        String text = "a".repeat(251);
        Message message = new Message("0000000000", 0, "+27718693002", text);
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", message.checkMessageLength());
    }

    @Test
    void testFirstMessageHash() {
        Message message = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", message.createMessageHash());
    }

    @Test
    void testSendIncrementsTotalAndReturnsSuccess() {
        Message message = new Message("0012345678", 0, "+27718693002", "Hello world");
        assertEquals("Message successfully sent.", message.SentMessage("Send"));
        assertEquals(1, message.returnTotalMessagess());
    }

    @Test
    void testStoreReturnsSuccessAndCreatesStoredStatus() {
        Message message = new Message("0012345678", 0, "+27718693002", "Hello world");
        assertEquals("Message successfully stored.", message.SentMessage("Store"));
        assertEquals("stored", message.getStatus());
    }

    @Test
    void testPrintMessagesContainsSentMessageDetails() {
        Message message = new Message("0012345678", 0, "+27718693002", "Hello world");
        message.SentMessage("Send");
        String output = message.printMessages();
        assertTrue(output.contains("Message ID: 0012345678"));
        assertTrue(output.contains("Message Hash: 00:0:HELLOWORLD"));
        assertTrue(output.contains("Recipient: +27718693002"));
        assertTrue(output.contains("Message: Hello world"));
    }

    @Test
    void testDisregardReturnsDeleteMessage() {
        Message message = new Message("0012345678", 0, "+27718693002", "Hello world");
        assertEquals("Press 0 to delete the message.", message.SentMessage("Disregard"));
        assertEquals(0, message.returnTotalMessagess());
    }
}
