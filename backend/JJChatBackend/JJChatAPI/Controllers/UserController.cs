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
        public JSONUser Get(JSONUser jsonUser) => new JSONUser(_controllerInstance.Login(jsonUser.Username, jsonUser.Password));

        // POST api/User
        public JSONUser Post(JSONUser jsonUser) => new JSONUser(_controllerInstance.Register(jsonUser.Username, jsonUser.Password));
    }
}