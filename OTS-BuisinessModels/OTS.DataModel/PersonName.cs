using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class PersonName
    {
        public virtual TitleType Title { get; set; }
        public virtual string FirstName { get; set; }
        public virtual string LastName { get; set; }
   
    }

    public enum TitleType
    {
        Dr,
        Mr,
        Ms,
        Miss,
        Professor
    }
}
