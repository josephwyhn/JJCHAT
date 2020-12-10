using JJChatAPI.InstanceManager;
using SharedData.Exceptions;
using SharedData.Interfaces;
using SharedData.Models.JSON;
using System;
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
        public JSONResponse Get(string username, string password)
        {
            var response = new JSONResponse(false, null);

            try
            {
                response.responseObject = new JSONUser(_controllerInstance.Login(username, password));
            }
            catch (JJLowPrioException jjexc)
            {
                response.isException = true;
                response.responseObject = new JSONJJLowPrioException(jjexc);
            }
            catch (Exception exc)
            {
                JJChatLoggerInstanceManager.GetInstance().Error("Fatal error occured in 'GET api/User'!", exc);
                throw exc;
            }

            return response;
        }

        // POST api/User
        public JSONResponse Post([FromBody]JSONUser jsonUser)
        {
            var response = new JSONResponse(false, null);

            try
            {
                response.responseObject = new JSONUser(_controllerInstance.Register(jsonUser?.username, jsonUser?.password));
            }
            catch (JJLowPrioException jjexc)
            {
                response.isException = true;
                response.responseObject = new JSONJJLowPrioException(jjexc);
            }
            catch (Exception exc)
            {
                JJChatLoggerInstanceManager.GetInstance().Error("Fatal error occured in 'POST api/User'!", exc);
                throw exc;
            }

            return response;
        }
    }
}