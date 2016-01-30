using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class CognitiveLevelType:Record
    {
        public virtual string Name { get; set; }
        public virtual string Description { get; set; }
    }
}
