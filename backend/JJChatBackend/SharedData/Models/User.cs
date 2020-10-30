using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SharedData.Models
{
    public class User
    {
        [Key]
        public long Id { get; set; }
        
        [Required]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }

        public virtual List<ChatMessage> SentMessages { get; set; }
        public virtual List<ChatMessage> ReceivedMessages { get; set; }
    }
}