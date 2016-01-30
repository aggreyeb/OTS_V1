using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class KnowledgeMap:Record 
    {
       
        public virtual string Name { get; set; }
        public virtual string Description { get; set; }
        public virtual User  CreatedBy { get; set; }
        public virtual DateTime CreateOn { get; set; }
        public virtual DateTime LastUpdated { get; set; }
        public virtual string Concepts { get; set; }
        public virtual bool IsPublic { get; set; }
    }
}
