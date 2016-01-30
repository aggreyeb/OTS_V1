using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OTS.DataModel;
using FluentNHibernate.Mapping;

namespace _602_EIM_Test.Maps
{
    public class UserMap:ClassMap<User>
    {
        public UserMap() {
            Id(x => x.Id).GeneratedBy.Identity().Column("UserId");
            Map(x => x.Email);
            Map(x => x.Phone).Length(15);
            Component(x => x.Name, n => {

                n.Map(x => x.FirstName).Length(60).Not.Nullable();
                n.Map(x => x.LastName).Length(60).Not.Nullable();
            });
            Component(x => x.Address, n => {
                n.Map(x => x.Number).Length(50);
                n.Map(x => x.Street).Length(100);
                n.Map(x => x.City).Length(60);
                n.Map(x => x.Province).Length(60);
            
            });
          
            References(x => x.UserType).Column("UserTypeId");
            References(x => x.UserAccount).Column("UserAccountId").Not.Nullable();

        }
    }
}
