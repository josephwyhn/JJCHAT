using JJChatAPI.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class UserController : ApiController
    {
        // GET api/User
        public bool Get(string username, string password)
        {
            using (var db = new JJChatContext())
            {
                db.Users.Add(new Models.User() { Username = "Test", Password = "Test123" });
                db.SaveChanges();
            }

            if (username == "Jeremy" && password == "test")
                return true;
            else
                return false;
        }

        // POST api/User
        public void Post(string username, string password)
        {
            throw new Exception("Test");
        }

        // DELETE api/User
        public void Delete(string username, string password, long id)
        {

        }
    }
}