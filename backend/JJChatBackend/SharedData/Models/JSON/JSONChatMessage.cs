using System;

namespace SharedData.Models.JSON
{
    public class JSONChatMessage
    {
        public long Id { get; set; }

        public DateTime Sent { get; set; }
        public string Message { get; set; }

        public bool Delivered { get; set; }

        public JSONUser Sender { get; set; }
        public JSONUser Receiver { get; set; }
    }
}