using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public class UserAccountMap:ClassMap<UserAccount>
    {
       public  UserAccountMap() {
           Id(x => x.Id).GeneratedBy.Identity().Column("UserAccountId");
           Map(x => x.UserName).Not.Nullable();
           Map(x=>x.Password).Not.Nullable();
           Map(x=>x.IsLocked).Default("false");
       
       }
    }
}
