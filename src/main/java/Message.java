
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a QuickChat message for Part 2.
 */
public class Message {

    private static final List<Message> SENT_MESSAGES = new ArrayList<>();
    private static int totalMessagesSent = 0;
    private static final String JSON_FILE = "messages.json";

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String status;

    /** Creates a message and generates a random 10-digit message ID. */
    public Message(String recipient, String message, int messageNumber) {
        this(generateMessageID(), messageNumber, recipient, message);
    }

    /** Constructor useful for deterministic unit tests. */
    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.status = "";
    }

    /** Compatibility constructor using message ID as the first argument. */
    public Message(String messageID, String recipient, String message, int messageNumber) {
        this(messageID, messageNumber, recipient, message);
    }

    /** Generates a random 10-digit message ID. */
    private static String generateMessageID() {
        long value = ThreadLocalRandom.current().nextLong(
                1_000_000_000L,
                10_000_000_000L
        );

        return String.valueOf(value);
    }

    /** Ensures the message ID contains exactly 10 numeric digits. */
    public Boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    /** Validates the recipient international number. */
    public String checkRecipientCell() {
        if (recipient == null || !recipient.matches("^\\+\\d{1,3}\\d{1,10}$")) {
            return "Recipient cell number is incorrectly formatted.";
        }

        return "Recipient successfully captured.";
    }

    /** Validates the 250-character message limit. */
    public String checkMessageLength() {
        if (message != null && message.length() <= 250) {
            return "Message ready to send.";
        }

        int length = message == null ? 0 : message.length();
        int excess = Math.max(0, length - 250);

        return "Message exceeds 250 characters by " + excess
                + "; please reduce the size.";
    }

    /** Creates the required message hash. */
    public String createMessageHash() {
        String idPart = messageID == null
                ? ""
                : messageID.substring(0, Math.min(2, messageID.length()));

        String firstWord = "";
        String lastWord = "";

        if (message != null) {
            String cleaned = message.trim();

            if (!cleaned.isEmpty()) {
                String[] words = cleaned.split("\\s+");

                firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
                lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
            }
        }

        messageHash = (idPart + ":" + messageNumber
                + ":" + firstWord + lastWord).toUpperCase();

        return messageHash;
    }

    /**
     * Records the selected message action.
     */
    public String SentMessage(String choice) {
        if (choice == null) {
            return "Invalid choice.";
        }

        String selected = choice.trim().toLowerCase();

        switch (selected) {
            case "send":
                status = "sent";

                if (!SENT_MESSAGES.contains(this)) {
                    SENT_MESSAGES.add(this);
                    totalMessagesSent++;
                }

                return "Message successfully sent.";

            case "disregard":
            case "discard":
                status = "disregarded";
                return "Press 0 to delete the message.";

            case "store":
                status = "stored";
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid choice.";
        }
    }

    /** Interactive version used by the console application. */
    public String SentMessage() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose Send, Disregard or Store: ");

        return SentMessage(scanner.nextLine());
    }

    /** Returns all messages sent during the current run. */
    public String printMessages() {
        if (SENT_MESSAGES.isEmpty()) {
            return "No messages have been sent.";
        }

        StringBuilder output = new StringBuilder();

        for (Message sent : SENT_MESSAGES) {
            output.append("Message ID: ")
                    .append(sent.messageID)
                    .append(System.lineSeparator());

            output.append("Message Hash: ")
                    .append(sent.messageHash)
                    .append(System.lineSeparator());

            output.append("Recipient: ")
                    .append(sent.recipient)
                    .append(System.lineSeparator());

            output.append("Message: ")
                    .append(sent.message)
                    .append(System.lineSeparator());

            output.append(System.lineSeparator());
        }

        return output.toString().trim();
    }

    /** Returns the total number of messages sent during the current run. */
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    /**
     * Stores this message in messages.json using Gson.
     * Existing stored messages are preserved.
     */
    public void storeMessage() {
        Path path = Paths.get(JSON_FILE);

        try {
            JsonArray messages;

            if (Files.exists(path)) {
                String existingJson = Files.readString(
                        path,
                        StandardCharsets.UTF_8
                ).trim();

                if (existingJson.isEmpty()) {
                    messages = new JsonArray();
                } else {
                    messages = JsonParser.parseString(existingJson)
                            .getAsJsonArray();
                }

            } else {
                messages = new JsonArray();
            }

            JsonObject messageObject = new JsonObject();

            messageObject.addProperty("messageID", messageID);
            messageObject.addProperty("messageNumber", messageNumber);
            messageObject.addProperty("recipient", recipient);
            messageObject.addProperty("message", message);
            messageObject.addProperty("messageHash", messageHash);
            messageObject.addProperty("status", status);

            messages.add(messageObject);

            Files.writeString(
                    path,
                    GSON.toJson(messages),
                    StandardCharsets.UTF_8
            );

        } catch (IOException | IllegalStateException
                | com.google.gson.JsonParseException exception) {

            throw new IllegalStateException(
                    "Unable to store message as JSON.",
                    exception
            );
        }
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getStatus() {
        return status;
    }

    /** Clears in-memory sent-message state for tests/new application runs. */
    public static void resetMessages() {
        SENT_MESSAGES.clear();
        totalMessagesSent = 0;
    }
}
