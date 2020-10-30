namespace SharedData.Models.JSON
{
    public class JSONUser
    {
        public long Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }

        public JSONUser() { }
        public JSONUser(User user)
        {
            Id = user.Id;
            Username = user.Username;
            Password = user.Password;
        }
    }
}