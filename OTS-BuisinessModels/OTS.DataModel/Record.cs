using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Serialization;
using System.Runtime.Serialization;

namespace OTS.DataModel
{
  
    [Serializable]
    public abstract  class Record
    {
        [XmlIgnore]
        public virtual int Id { get; set; }
      //  public virtual Guid Key { get; set; }
        public virtual string ToXml()
        {
            var sw = new System.IO.StringWriter();
            var serializer = new XmlSerializer(this.GetType());
            serializer.Serialize(sw, this);
            return sw.ToString();
        }

        public virtual T FromXml<T>(string xmlString)
        {
            var stringReader = new System.IO.StringReader(xmlString);
            var serializer = new XmlSerializer(typeof(T));
            return (T)serializer.Deserialize(stringReader);
        }

       
         
    }
}
