using SharedData.Exceptions;

namespace SharedData.Models.JSON
{
    public class JSONJJLowPrioException
    {
        public string message { get; set; }

        public JSONJJLowPrioException() { }

        public JSONJJLowPrioException(JJLowPrioException jjexc)
        {
            message = jjexc.Message;
        }
    }
}