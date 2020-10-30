using SharedData.Interfaces;
using System;
using System.ComponentModel.DataAnnotations;

namespace SharedData.Models
{
    public class ChatMessage : IMessage
    {
        [Key]
        public long Id { get; set; }

        public DateTime Sent { get; set; }
        public string Message { get; set; }
        public bool Delivered { get; set; }
        
        public long SenderId { get; set; }
        public long ReceiverId { get; set; }
        
        public virtual User Sender { get; set; }        
        public virtual User Receiver { get; set; }
    }
}