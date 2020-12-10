using System;

namespace SharedData.Exceptions
{
    [Serializable]
    public class JJLowPrioException : Exception
    {
        public JJLowPrioException() { }

        public JJLowPrioException(string message) : base(message) { }

        public JJLowPrioException(string message, Exception innerException) : base(message, innerException) { }
    }
}