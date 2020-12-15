using SharedData.Exceptions;
using System.Collections.Generic;
using System.Linq;

namespace SharedData.Models.JSON
{
    public class JSONChatMessageList
    {
        public List<JSONChatMessage> sentMessages { get; set; }
        public List<JSONChatMessage> receivedMessages { get; set; }

        public JSONChatMessageList()
        {
            sentMessages = new List<JSONChatMessage>();
            receivedMessages = new List<JSONChatMessage>();
        }

        public JSONChatMessageList(IEnumerable<ChatMessage> sentMessages, IEnumerable<ChatMessage> receivedMessages)
        {
            if (sentMessages == null)
                throw new JJLowPrioException("Gesendete Nachrichten dürfen nicht null sein!");

            if (receivedMessages == null)
                throw new JJLowPrioException("Empfange Nachrichten dürfen nicht null sein!");

            this.sentMessages = sentMessages.Select(x => new JSONChatMessage
            {
                id = x.Id,
                delivered = x.Delivered,
                sent = x.Sent,
                message = x.Message,
                sender = x.SenderId,
                receiver = x.ReceiverId
            }).ToList();

            this.receivedMessages = receivedMessages.Select(x => new JSONChatMessage
            {
                id = x.Id,
                delivered = x.Delivered,
                sent = x.Sent,
                message = x.Message,
                sender = x.SenderId,
                receiver = x.ReceiverId
            }).ToList();
        }
    }
}