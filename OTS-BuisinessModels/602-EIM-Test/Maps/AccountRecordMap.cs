using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FluentNHibernate.Mapping;
using OTS.DataModel;

namespace _602_EIM_Test.Maps
{
    public  class AccountRecordMap:ClassMap<Account>
    {
        public AccountRecordMap() {
            Table("Account");
            Id(x => x.Id).GeneratedBy.Identity().Column("AccountId");
            Map(x => x.UserName).Length(100);
            Map(x => x.Password).Length(100);
            Map(x => x.IsLock);
           
        }
    }
}
