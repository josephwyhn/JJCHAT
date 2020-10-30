using SharedData.Models;
using System.Data.Entity;

namespace DataAccess.DBContext
{
    public class JJChatContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<ChatMessage> Messages { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ChatMessage>()
                .HasRequired(m => m.Sender)
                .WithMany(t => t.SentMessages)
                .HasForeignKey(m => m.SenderId)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ChatMessage>()
                .HasRequired(m => m.Receiver)
                .WithMany(t => t.ReceivedMessages)
                .HasForeignKey(m => m.ReceiverId)
                .WillCascadeOnDelete(false);
        }
    }
}