using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class UserType:Record 
    {
        public virtual string Name { get; set; }
        public virtual string HomePageName { get; set; }
    }
}
