using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class TestAnswerSheet:Record
    {
      
        public virtual Test Test { get; set; }
        public virtual TestItem TestItem { get; set; }
        public virtual int LineNumber { get; set; }
        public virtual bool A { get; set; }
        public virtual bool B { get; set; }
        public virtual bool C { get; set; }
        public virtual bool D { get; set; }
    } 
}
