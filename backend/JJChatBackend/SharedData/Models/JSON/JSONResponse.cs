namespace SharedData.Models.JSON
{
    public class JSONResponse
    {
        public JSONResponse() { }

        public JSONResponse(bool isException, object responseObject)
        {
            this.isException = isException;
            this.responseObject = responseObject;
        }

        public bool isException { get; set; }
        public object responseObject { get; set; }
    }
}