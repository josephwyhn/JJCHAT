using DataAccess;
using JJChatAPI.DataAccess;
using SharedData.Interfaces;
using SharedData.Models;
using SharedData.Models.JSON;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class UserController : ApiController
    {
        private IJJChatController _controllerInstance;

        public UserController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/User
        public JSONUser Get(string username, string password) => new JSONUser(_controllerInstance.Login(username, password));

        // POST api/User
        public JSONUser Post([FromBody]JSONUser jsonUser) => new JSONUser(_controllerInstance.Register(jsonUser?.username, jsonUser?.password));
    }
}