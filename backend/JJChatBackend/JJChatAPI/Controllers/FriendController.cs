using JJChatAPI.InstanceManager;
using SharedData.Exceptions;
using SharedData.Interfaces;
using SharedData.Models.JSON;
using System;
using System.Web.Http;

namespace JJChatAPI.Controllers
{
    public class FriendController : ApiController
    {
        private IJJChatController _controllerInstance;

        public FriendController()
        {
            _controllerInstance = JJChatControllerInstanceManager.GetInstance();
        }

        // GET api/Friend
        public JSONResponse Get(string username)
        {
            var response = new JSONResponse(false, null);

            try
            {
                response.responseObject = _controllerInstance.GetFriend(username);
            }
            catch (JJLowPrioException jjexc)
            {
                response.isException = true;
                response.responseObject = new JSONJJLowPrioException(jjexc);
            }
            catch (Exception exc)
            {
                JJChatLoggerInstanceManager.GetInstance().Error("Fatal error occured in 'GET api/Friend'!", exc);
                throw exc;
            }

            return response;
        }
    }
}