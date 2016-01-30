using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class Test:Record 
    {
        public virtual string Name { get; set; }
        public virtual  float TotalMark{ get;set;}
        public virtual int NumberOfQuestion { get; set; }
        public virtual  DateTime StartDate { get; set; }
        public virtual string StartTime { get; set; }
        public virtual string EndTime{ get; set; }
        public virtual bool IsActivated { get; set; }
        public virtual List<TestItem> Questions { get; set; }
    }
}
