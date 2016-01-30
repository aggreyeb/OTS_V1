using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public class UserAccount:Record 
    {
        public virtual string UserName { get; set; }
        public virtual string Password { get; set; }
        public virtual bool IsLocked { get; set; }
    }
}
