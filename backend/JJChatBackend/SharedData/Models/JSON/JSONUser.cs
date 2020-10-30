namespace SharedData.Models.JSON
{
    public class JSONUser
    {
        public long id { get; set; }
        public string username { get; set; }
        public string password { get; set; }

        public JSONUser() { }
        public JSONUser(User user)
        {            
            id = user == null ? -1 : user.Id;
            username = user?.Username;
            password = user?.Password;
        }
    }
}