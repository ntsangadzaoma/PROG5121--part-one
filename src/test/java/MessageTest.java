
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void reset() {
        Message.resetMessages();
    }

    @Test
    public void testGeneratedMessageID() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testMessageIDTooLong() {
        Message message = new Message(
                "12345678901",
                0,
                "+27718693002",
                "Hello"
        );

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testMessageIDTooShort() {
        Message message = new Message(
                "12345",
                0,
                "+27718693002",
                "Hello"
        );

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testMessageIDContainsLetters() {
        Message message = new Message(
                "12345ABCDE",
                0,
                "+27718693002",
                "Hello"
        );

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                "1234567890",
                0,
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageLengthValid() {
        String messageText = "A".repeat(250);

        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                messageText
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthInvalid() {
        String messageText = "A".repeat(251);

        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                messageText
        );

        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash()
        );
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("Send")
        );

        assertEquals(1, message.returnTotalMessagess());
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("Store")
        );

        assertEquals("stored", message.getStatus());
    }

    @Test
    public void testStoreMultipleMessagesAsJson() throws IOException {
        Path path = Paths.get("messages.json");
        Files.deleteIfExists(path);

        Message first = new Message(
                "1234567890",
                0,
                "+27718693002",
                "First message"
        );

        Message second = new Message(
                "0987654321",
                1,
                "+27834567890",
                "Second message"
        );

        first.SentMessage("Store");
        second.SentMessage("Store");

        String json = Files.readString(path, StandardCharsets.UTF_8);
        JsonArray messages = JsonParser.parseString(json).getAsJsonArray();

        assertEquals(2, messages.size());

        JsonObject firstMessage = messages.get(0).getAsJsonObject();
        JsonObject secondMessage = messages.get(1).getAsJsonObject();

        assertEquals("1234567890", firstMessage.get("messageID").getAsString());
        assertEquals("First message", firstMessage.get("message").getAsString());

        assertEquals("0987654321", secondMessage.get("messageID").getAsString());
        assertEquals("Second message", secondMessage.get("message").getAsString());

        Files.deleteIfExists(path);
    }

    @Test
    public void testStoreMessageWithSpecialCharacters() throws IOException {
        Path path = Paths.get("messages.json");
        Files.deleteIfExists(path);

        String originalMessage = "Hi Mike, \"How are you?\"\nSee you soon!";

        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                originalMessage
        );

        message.SentMessage("Store");

        String json = Files.readString(path, StandardCharsets.UTF_8);
        JsonArray messages = JsonParser.parseString(json).getAsJsonArray();

        assertEquals(
                originalMessage,
                messages.get(0).getAsJsonObject()
                        .get("message").getAsString()
        );

        Files.deleteIfExists(path);
    }

    @Test
    public void testPrintMessages() {
        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                "Hello"
        );

        message.SentMessage("Send");

        String output = message.printMessages();

        assertTrue(output.contains("Message ID:"));
        assertTrue(output.contains("Message Hash:"));
        assertTrue(output.contains("Recipient:"));
        assertTrue(output.contains("Message:"));
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567890",
                0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("Disregard")
        );

        assertEquals(0, message.returnTotalMessagess());
    }
}
