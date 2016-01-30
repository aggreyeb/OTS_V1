using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{
    public class UserTypeMap:ClassMap<UserType>
    {
        public UserTypeMap()
        {
            Id(x => x.Id).GeneratedBy.Identity().Column("UserTypeId");
            Map(x => x.Name).Length(100);
            Map(x => x.HomePageName);
        }
    }
}
