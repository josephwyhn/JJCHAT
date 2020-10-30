using System;

namespace SharedData.Models.JSON
{
    public class JSONChatMessage
    {
        public long id { get; set; }

        public DateTime sent { get; set; }
        public string message { get; set; }

        public bool delivered { get; set; }

        public JSONUser sender { get; set; }
        public JSONUser receiver { get; set; }
    }
}