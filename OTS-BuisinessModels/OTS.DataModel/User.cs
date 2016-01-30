using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OTS.DataModel
{
    public  class User{
    
       
        public virtual int Id { get; set; }
        public virtual PersonName Name{get;set;}
        public virtual Address Address{get;set;}
        public virtual string Phone{get;set;}
        public virtual string Email{get;set;}
        public virtual UserType UserType { get; set; }
        public virtual UserAccount UserAccount { get; set; }
        
    }

}
